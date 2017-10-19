package com.accenture.aris.sample.business.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.sample.business.entity.UserEntity;
import com.accenture.aris.sample.business.entity.UserListEntity;

public interface UserService extends Serializable {

    public ServiceResult<UserEntity> searchUserService(String id);
    
    public ServiceResult<UserEntity> searchUserService(String name, String password);

    public ServiceResult<List<UserEntity>> searchUserListService(UserEntity entity);
    
    public ServiceResult<List<String>> searchAmbiguousUserService(String id);
    
    public ServiceResult<Void> searchUserPageService(UserListEntity entity, int page);
    
    public ServiceResult<Boolean> saveUserService(UserEntity entity);

    public ServiceResult<Boolean> deleteUserService(String id);

    public ServiceResult<Boolean> updateUserService(UserEntity entity);

    public ServiceResult<Boolean> updateUserOnFormService(UserEntity entity);
    
    public ServiceResult<Boolean> sendMail(Map<String, Object> map) throws IOException, GeneralSecurityException;
    
    public ServiceResult<Boolean> downloadWithHandlerService(UserEntity entity, OutputStream outputStream) throws IOException;
    
    public ServiceResult<Boolean> userLoadService(File file) throws IOException;
    
}
