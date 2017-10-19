package com.accenture.aris.sample.mvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.accenture.aris.core.support.message.Messages;
import com.accenture.aris.sample.business.service.UserService;
import com.accenture.aris.sample.mvc.form.UserSearchForm;
import com.accenture.aris.sample.mvc.form.UserUploadForm;

@Controller
@RequestMapping(value = "/user")
public class UserUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserUploadController.class);
    
    @Value("${upload.tmpDirPath}")
    private String tmpDirPath = "/tmp";
    
    @Autowired
    private Messages messages;

    @Autowired
    UserService userService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    @RequestMapping(value = "/upload")
    public String uploadAction(UserUploadForm userUploadForm, BindingResult result, HttpServletRequest request, Model uiModel, RedirectAttributes attributes) throws IOException {

        if(userUploadForm.getUploadFile() != null 
                && userUploadForm.getUploadFile().getSize() > 0) {
            InputStream is = userUploadForm.getUploadFile().getInputStream();
            File tmpDir = new File(tmpDirPath);
            File newFile = File.createTempFile("FILE_UPLOAD.", ".tmp", tmpDir);
            OutputStream os = new FileOutputStream(newFile);
            FileCopyUtils.copy(is,os);
            
            try {
                userService.userLoadService(newFile);
            } catch (Exception e) {
                attributes.addFlashAttribute("message", "The user load is failed. Please check the error message." + e.getMessage());
            }
            try {
                newFile.delete();
            } catch(Exception e) {
                e.printStackTrace();
            }
            
            
        } else {
            attributes.addFlashAttribute("message","The upload file is empty. Please set the upload file name.");
        }
        
        return "redirect:/user/";
    }
    
    @RequestMapping(value = "/uploadInput")
    public String uploadInputAction(UserSearchForm userSearchForm, BindingResult result, Model uiModel) {
    
        return "user/userUploadInput";
        
    }
}
