package com.accenture.aris.sample.business.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.constraint.Unique;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.accenture.aris.core.BusinessFailureException;
import com.accenture.aris.core.GeneralFailureException;
import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.core.support.excel.ExcelUtils;
import com.accenture.aris.core.support.mail.JavaMailSenderWrapper;
import com.accenture.aris.core.support.pagination.PaginationUtils;
import com.accenture.aris.sample.business.entity.UserEntity;
import com.accenture.aris.sample.business.entity.UserListEntity;
import com.accenture.aris.sample.business.handler.UserHandler;
import com.accenture.aris.sample.business.repository.UserRepository;
import com.accenture.aris.sample.business.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final long serialVersionUID = 1L;
    
    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSenderWrapper javaMailSender;
    
    @Override
    public ServiceResult<UserEntity> searchUserService(String id) {
        if (id == null || id.equals("")) {
            return new ServiceResult<UserEntity>();
        }
        return new ServiceResult<UserEntity>(userRepository.selectByPrimaryKey(id));
    }
    
    @Override
    public ServiceResult<UserEntity> searchUserService(String name, String password) {
        return new ServiceResult<UserEntity>(userRepository.selectByNameAndPassword(name, password));
    }

    @Override
    public ServiceResult<List<UserEntity>> searchUserListService(UserEntity entity) {
        if (entity == null) {
            return new ServiceResult<List<UserEntity>>();
        }
        return new ServiceResult<List<UserEntity>>(userRepository.selectByEntity(entity));
    }

    @Override
    public ServiceResult<List<String>> searchAmbiguousUserService(String id) {
        return new ServiceResult<List<String>>(userRepository.selectByPrimaryKeyAmbiguous(id));
    }
    
    @Override
    public ServiceResult<Void> searchUserPageService(UserListEntity entity, int page){
        if (entity == null) return new ServiceResult<Void>();

        int recordCount = userRepository.countByEntityWithCode(entity);
        int disp = 5;
        int offset = (page -1) * disp;
        int limit = disp;
        
        ServiceResult<Void> result = new ServiceResult<Void>();
        result.setAttribute("users", userRepository.selectByEntityWithCode(entity, new RowBounds(offset, limit)));
        result.setAttribute("pages", PaginationUtils.pagination(recordCount, page, disp));
        
        return result;
    }
    
    @Override
    public ServiceResult<Boolean> saveUserService(UserEntity entity) {
        if (entity == null) {
            return new ServiceResult<Boolean>(false);
        }
        int count = userRepository.insert(entity);
        if (count == 1) {
            LOGGER.debug("save entity={}.", entity);
            return new ServiceResult<Boolean>(true);
        } else if (count > 1) {
            throw new BusinessFailureException("update failure. torasaction rollback.");
        } else {
            LOGGER.debug("update failue. entity={}.", entity);
            return new ServiceResult<Boolean>(false);
        }
    }

    @Override
    public ServiceResult<Boolean> deleteUserService(String id) {
        if (id == null) {
            return new ServiceResult<Boolean>(false);
        }
        userRepository.deleteByPrimaryKey(id);
        return new ServiceResult<Boolean>(true);
    }

    @Override
    public ServiceResult<Boolean> updateUserService(UserEntity entity) {
        if (entity == null) {
            return new ServiceResult<Boolean>(false);
        }
        userRepository.updateByPrimaryKey(entity);
        return new ServiceResult<Boolean>(true);
    }
    
    @Override
    public ServiceResult<Boolean> updateUserOnFormService(UserEntity entity) {
        if (entity == null) {
            return new ServiceResult<Boolean>(false);
        }
        int count = userRepository.updateByPrimaryKeySelectiveOnForm(entity);
        if (count > 1) {
            throw new GeneralFailureException();
        }
        return count == 1 ? new ServiceResult<Boolean>(true) : new ServiceResult<Boolean>(false);
    }
    
    @Override
    public ServiceResult<Boolean> sendMail(Map<String, Object> model) throws IOException, GeneralSecurityException {
        
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/userList.xls");
        DataSource dataSource = new ByteArrayDataSource(ExcelUtils.transform(inputStream, model), "application/xls");
        javaMailSender.sendWithAttachement("yuya.miura@accenture.com", "winclue56@gmail.com", "This is a test Message.", dataSource, "userList.xls", "template/userList.vm", model);
        
        return new ServiceResult<Boolean>(true);
    }
    
    @Override
    public ServiceResult<Boolean> downloadWithHandlerService(UserEntity entity, OutputStream outputStream) throws IOException {
        File downloadFile = null;
        boolean isSuccess = false;
        try{
            UserHandler handler = new UserHandler();
            handler.init();
            userRepository.selectByEntityWithHandler(entity, handler);
            downloadFile = handler.download();
            FileCopyUtils.copy(new FileSystemResource(downloadFile).getInputStream(), outputStream);
            isSuccess = true;
        } finally {
            if (isSuccess == true) {
                try {
                    if (downloadFile != null && downloadFile.exists() == true) {
                        downloadFile.delete();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else {
                LOGGER.error("Download failure.");
            }
        }
        return new ServiceResult<Boolean>(true);
    }
    
    @Override
    public ServiceResult<Boolean> userLoadService(File file) {
        CellProcessor[] cellProcessors = new CellProcessor[] {
                new Unique(new StrMinMax(1, 5)), // id
                new Optional(new StrMinMax(0, 16)), // name
                new Optional(new StrMinMax(0, 50)), // password
                new Optional(new StrMinMax(0, 5)), // roleId
                new Optional(new StrMinMax(0, 64)), // email
                new Optional(new StrMinMax(0, 10)), // sex
                new Optional(new StrMinMax(0, 10)), // nationality
                new Optional(new StrMinMax(0, 100)), // text
                new Optional(new StrMinMax(0, 2)), // defkey
                new Optional(new ParseDate("yyyy/MM/dd")), // startDate
                new Optional(new ParseDate("yyyy/MM/dd")) // endDate
        };
        boolean successful = true;
        String message = "";
        String[] header = new String[]{"id", "name", "password", "roleId", "email", "sex", "nationality", "text", "defkey", "startDate", "endDate"};
        CsvBeanReader inFile = null;
        try {
            inFile = new CsvBeanReader(new InputStreamReader(new FileInputStream(file), "UTF-8"), CsvPreference.EXCEL_PREFERENCE);
            inFile.getHeader(true);
            UserEntity userEntity;
            while((userEntity = inFile.read(UserEntity.class, header, cellProcessors)) != null) {
                try {
                    userRepository.insert(userEntity);
                } catch(Exception e) {
                    message = message + e.getMessage();
                    successful = false;
                }
            }
        } catch(IOException ex) {
            LOGGER.info("catch IOException. ex={}", ex);
            throw new GeneralFailureException("Catch IOException.", ex);
        } finally {
            if (inFile != null) {
                try {
                    inFile.close();
                } catch(IOException ex) {
                    // nothing
                }
            }
        }
        if (!successful) {
            ServiceResult<Boolean> result = new ServiceResult<Boolean>(false);
            result.setAttribute("message", message);
            return result;
        }
        return new ServiceResult<Boolean>(true);
    }
}