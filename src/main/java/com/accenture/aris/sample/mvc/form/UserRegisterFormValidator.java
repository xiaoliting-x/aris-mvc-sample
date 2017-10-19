package com.accenture.aris.sample.mvc.form;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.core.support.message.Messages;
import com.accenture.aris.sample.business.entity.UserEntity;
import com.accenture.aris.sample.business.service.UserService;

@Component
public class UserRegisterFormValidator implements Serializable {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterFormValidator.class);
    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private Messages messages;
    
    @Autowired
    private UserService service;

    public void validateConfirmedPassword(UserRegisterForm form, BindingResult result, Model uiModel) {
        // 全ての入力項目でエラーがない場合に独自エラーバリデーションを実行する
        if (result.hasErrors() == true) {
            return;
        }
        if (form.getConfirmedPassword().equals(form.getPassword()) == false) {
            // passwordが一致していない場合にエラーメッセージをセット
            FieldError error = new FieldError("userRegistForm", "confirmedPassword", messages.getMessage("E00002"));
            result.addError(error);
        }
    }
    
    public boolean validateDuplicateId(UserRegisterForm form, BindingResult result, Model uiModel) {
        if (result.hasErrors() == true) {
            return false;
        }
        ServiceResult<UserEntity> serviceResult = service.searchUserService(form.getId());
        if(serviceResult.getResult() != null) {
            uiModel.addAttribute("message", messages.getMessage("W00001"));
            LOGGER.debug("the userid is already exist.");
            form.setId(null);
            return false;
        }
        return true;
    }
}
