package com.accenture.aris.sample.mvc.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.core.support.codeloader.CodeLoader;
import com.accenture.aris.core.support.message.Messages;
import com.accenture.aris.sample.mvc.form.UserSearchForm;
import com.accenture.aris.sample.mvc.form.UserUploadForm;
import com.accenture.aris.sample.business.service.UserService;
import com.accenture.aris.sample.business.entity.UserListEntity;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = "userSearchForm")
public class UserSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSearchController.class);

    @Autowired
    private Messages messages;

    @Autowired
    UserService userService;

    @Autowired
    CodeLoader codeLoader;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/list")
    public String listAction(UserSearchForm userSearchForm, BindingResult result, Model uiModel) {
        
        ServiceResult<Void> serviceResult = userService.searchUserPageService(new UserListEntity(), 1);
        
        uiModel.addAttribute("users", serviceResult.getAttribute("users"));
        uiModel.addAttribute("userSearchForm", userSearchForm);
        uiModel.addAttribute("userUploadForm", new UserUploadForm());
        uiModel.addAttribute("pages", serviceResult.getAttribute("pages"));
        
        return "user/userList";
    }

    @RequestMapping(value = "")
    public String userAction(UserSearchForm userSearchForm, BindingResult result, Model uiModel, SessionStatus status) {
        
        uiModel.addAttribute("userUploadForm", new UserUploadForm());
        status.setComplete();
        
        return "user/userList";
    }

    @RequestMapping(value = "/search")
    public String searchAction(UserSearchForm userSearchForm, BindingResult result, Model uiModel) {
        
        UserListEntity searchUserListEntity = new UserListEntity();
        BeanUtils.copyProperties(userSearchForm, searchUserListEntity);
        
        ServiceResult<Void> serviceResult = userService.searchUserPageService(searchUserListEntity, 1);
        
        uiModel.addAttribute("users", serviceResult.getAttribute("users"));
        uiModel.addAttribute("userSearchForm", userSearchForm);
        uiModel.addAttribute("userUploadForm", new UserUploadForm());
        uiModel.addAttribute("pages", serviceResult.getAttribute("pages"));
        
        return "user/userList";
    }
    
    @RequestMapping(value = "/search/{page}")
    public String searchPageAction(@PathVariable("page") int page, UserSearchForm userSearchForm, BindingResult result, Model uiModel) {

        UserListEntity searchUserListEntity = new UserListEntity();
        BeanUtils.copyProperties(userSearchForm, searchUserListEntity);

        ServiceResult<Void> serviceResult = userService.searchUserPageService(searchUserListEntity, page);
        
        uiModel.addAttribute("users", serviceResult.getAttribute("users"));
        uiModel.addAttribute("userSearchForm",userSearchForm);
        uiModel.addAttribute("userUploadForm", new UserUploadForm());
        uiModel.addAttribute("pages", serviceResult.getAttribute("pages"));
        
        return "user/userList";
    }
    
    @RequestMapping(value = "/ajax")
    public @ResponseBody List<String> ajaxAction(@RequestParam("term") String query, Model uiModel) {
        
        uiModel.addAttribute("userUploadForm", new UserUploadForm());
        ServiceResult<List<String>> serviceResult = userService.searchAmbiguousUserService(query);

        return serviceResult.getResult();
    }
}
