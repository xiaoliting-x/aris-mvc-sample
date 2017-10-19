package com.accenture.aris.sample.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.accenture.aris.core.support.message.Messages;
import com.accenture.aris.sample.mvc.form.UserSearchForm;
import com.accenture.aris.sample.business.entity.UserEntity;
import com.accenture.aris.sample.business.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserDownloadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDownloadController.class);

    @Autowired
    private Messages messages;

    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    @RequestMapping(value = "/download")
    public void downloadAction(UserSearchForm userSearchForm, BindingResult result, Model uiModel, HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userSearchForm, userEntity);
        response.setHeader("Content-Disposition", "attachment; filename=" + "user.csv");
        response.setContentType("text/csv");
        userService.downloadWithHandlerService(userEntity, response.getOutputStream());

        return ;
    }
}
