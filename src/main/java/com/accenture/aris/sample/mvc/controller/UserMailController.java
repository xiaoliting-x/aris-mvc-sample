package com.accenture.aris.sample.mvc.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.core.support.message.Messages;
import com.accenture.aris.sample.business.entity.UserEntity;
import com.accenture.aris.sample.business.service.UserService;
import com.accenture.aris.sample.mvc.form.UserSearchForm;

@Controller
@RequestMapping(value = "/user")
public class UserMailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDownloadController.class);
    
    @Autowired
    private Messages messages;

    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    @RequestMapping(value = "/mail")
    public String mail(UserSearchForm userSearchForm, BindingResult result, Model uiModel) throws IOException, GeneralSecurityException {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userEntity, userSearchForm);
        ServiceResult<List<UserEntity>> serviceResult = userService.searchUserListService(userEntity);
        uiModel.addAttribute("users", serviceResult.getResult());
        userService.sendMail(uiModel.asMap());
        return "redirect:/user/search";
    }

}
