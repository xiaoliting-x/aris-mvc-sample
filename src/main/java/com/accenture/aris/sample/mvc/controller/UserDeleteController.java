package com.accenture.aris.sample.mvc.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.accenture.aris.core.support.codeloader.CodeLoader;
import com.accenture.aris.core.support.codeloader.StaticCodeLoader;
import com.accenture.aris.core.support.message.Messages;
import com.accenture.aris.sample.mvc.form.UserDeleteForm;
import com.accenture.aris.sample.business.entity.UserEntity;
import com.accenture.aris.sample.business.service.UserService;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = "userDeleteForm")
public class UserDeleteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDeleteController.class);

    @Autowired
    private Messages messages;

    @Autowired
    UserService userService;

    @Autowired
    CodeLoader codeLoader;

    @Autowired
    StaticCodeLoader staticCodeLoader;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    @RequestMapping(value = "/delete")
    public String delete(UserDeleteForm userDeleteForm, BindingResult result, Model uiModel, SessionStatus status) {
        userService.deleteUserService(userDeleteForm.getId());
        status.setComplete();
        return "redirect:/user";
    }

    @RequestMapping(value = "/deleteConfirm/{id}")
    public String deleteConfirm(@PathVariable("id") String id, UserDeleteForm userDeleteForm, Model uiModel) {
        UserEntity userEntity = userService.searchUserService(id).getResult();
        BeanUtils.copyProperties(userEntity, userDeleteForm);
        return "user/userDeleteConfirm";
    }
}
