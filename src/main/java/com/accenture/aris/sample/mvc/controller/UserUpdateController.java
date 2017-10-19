package com.accenture.aris.sample.mvc.controller;

import java.util.Date;

import javax.validation.Valid;

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

import com.accenture.aris.sample.mvc.form.UserUpdateForm;
import com.accenture.aris.sample.business.entity.UserEntity;
import com.accenture.aris.sample.business.service.RoleService;
import com.accenture.aris.sample.business.service.UserService;


@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = "userUpdateForm")
public class UserUpdateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserUpdateController.class);

    @Autowired
    private Messages messages;

    @Autowired
    UserService userService;

    @Autowired
    StaticCodeLoader staticCodeLoader;

    @Autowired
    CodeLoader codeLoader;

    @Autowired
    private RoleService roleService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    @RequestMapping(value = "/updateConfirm")
    public String updateConfirm(@Valid UserUpdateForm userUpdateForm, BindingResult result, Model uiModel, SessionStatus status) {
        if(result.hasErrors()) {
            LOGGER.info("invalid request.");
            setInitialValue(uiModel);
            status.setComplete();
            return "user/userUpdateInput";
        }
        setDataFromCode(userUpdateForm);
        return "user/userUpdateConfirm";
    }

    @RequestMapping(value = "/update")
    public String update(UserUpdateForm userUpdateForm, Model uiModel, SessionStatus status) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userUpdateForm, userEntity);

        Date startDate = new Date();
        userEntity.setStartDate(startDate);

        try {
            userService.updateUserOnFormService(userEntity);
        } catch(Exception e) {
            return "user/userUpdateConfirm";
        } finally {
            status.setComplete();
        }
        return "redirect:/user/detail/" + userUpdateForm.getId();
    }

    @RequestMapping(value = "/updateInput/{id}")
    public String updateInput(@PathVariable("id") String id, UserUpdateForm userUpdateForm, Model uiModel, SessionStatus status) {
        BeanUtils.copyProperties(userService.searchUserService(id).getResult(), userUpdateForm);
        setInitialValue(uiModel);
        status.setComplete();
        return "user/userUpdateInput";
    }
    
    @RequestMapping(value = "/updateReInput")
    public String updateReInput(Model uiModel, SessionStatus status) {
        setInitialValue(uiModel);
        status.setComplete();
        return "user/userUpdateInput";
    }
    
    private void setInitialValue(Model uiModel) {
        uiModel.addAttribute("roles", roleService.selectRoleByEntity().getResult());
        uiModel.addAttribute("sexs", staticCodeLoader.getCodeDataList("sex"));
        uiModel.addAttribute("nationalities", staticCodeLoader.getCodeDataList("nationality"));
    }

    private void setDataFromCode(UserUpdateForm userUpdateForm) {
        userUpdateForm.setLabelRoleId(roleService.getRoleName(userUpdateForm.getRoleId()));
        userUpdateForm.setLabelSex(staticCodeLoader.getCodeDataValue("sex", userUpdateForm.getSex()));
        userUpdateForm.setLabelNationality(staticCodeLoader.getCodeDataValue("nationality", userUpdateForm.getNationality()));
    }
}
