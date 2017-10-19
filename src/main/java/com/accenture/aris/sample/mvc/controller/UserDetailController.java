package com.accenture.aris.sample.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.accenture.aris.core.support.codeloader.CodeLoader;
import com.accenture.aris.core.support.codeloader.StaticCodeLoader;
import com.accenture.aris.core.support.message.Messages;

import com.accenture.aris.sample.mvc.form.UserDetailForm;
import com.accenture.aris.sample.business.service.UserService;


@Controller
@RequestMapping(value = "/user")
public class UserDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailController.class);

    @Autowired
    private Messages messages;

    @Autowired
    UserService userService;

    @Autowired
    StaticCodeLoader staticCodeLoader;

    @Autowired
    CodeLoader codeLoader;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    @RequestMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") String id, UserDetailForm userDetailForm, Model uiModel) {
        BeanUtils.copyProperties(userService.searchUserService(id).getResult(), userDetailForm);
        return "user/userDetail";
    }
}
