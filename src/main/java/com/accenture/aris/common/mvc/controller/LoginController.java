package com.accenture.aris.common.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.accenture.aris.core.GeneralFailureException;
import com.accenture.aris.core.authentication.AuthenticatedLocator;
import com.accenture.aris.core.authentication.Authenticator;
import com.accenture.aris.core.authentication.AuthenticatorData;
import com.accenture.aris.core.authentication.ServletAuthenticatedLocator;
import com.accenture.aris.core.authorization.Authorization;
import com.accenture.aris.core.authorization.AuthorizationData;
import com.accenture.aris.core.authorization.ServletAuthorisedLocator;
import com.accenture.aris.core.support.message.Messages;
import com.accenture.aris.common.mvc.form.LoginForm;

@Controller
@RequestMapping(value = "/")
@SessionAttributes(value="loginForm")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private Messages messages;

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private Authorization authorization;
    
    @RequestMapping(value = "/")
    public String indexAction(LoginForm loginForm, BindingResult results, Model uiModel) {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String loginAction(@Valid LoginForm loginForm, BindingResult results, Model uiModel, HttpServletRequest request) {

        // check input error
        if(results.hasErrors()) {
            LOGGER.debug("invalid request.");
            return "index";
        }
        
        try {
            // execute Authentication
            AuthenticatorData user = authenticator.authenticate(loginForm.getName(), loginForm.getPassword());
            if (user == null) {
                LOGGER.debug("Authentication : Failure");
                uiModel.addAttribute("message", messages.getMessage("I00002"));         
                return "index";
            }
            LOGGER.debug("Authentication : Successful");
            
            // execute Authorization
            AuthorizationData role = authorization.authorize(loginForm.getName());
            if (role == null || role.getRoleId() == null) {
                LOGGER.debug("Authorization : Failure");
                uiModel.addAttribute("message", messages.getMessage("W00002"));         
                return "index";
            }
            LOGGER.debug("Authorization : Successful");
            
            // Store session User id and Role id 
            new ServletAuthenticatedLocator(request).saveOrUpadateAuthenticatedUser(user);
            new ServletAuthorisedLocator(request).saveOrUpadateAuthorisedRole(role);
            
            // password delete
            loginForm.setPassword(null);
            
            LOGGER.debug("login Successful.");
            return "redirect:top";
            
        } catch(Throwable th) {
            if (th instanceof org.mybatis.spring.MyBatisSystemException) {
                th.printStackTrace();
                uiModel.addAttribute("message", "Can not get jdbc connetion. Database connetion is refused.");          
                return "index";
            }
            if (th instanceof RuntimeException) {
                throw (RuntimeException)th;
            } else {
                throw new GeneralFailureException(th);
            }
        }
    }

    @RequestMapping(value = "/logout")
    public String logoutAction(LoginForm loginForm, BindingResult result, Model uiModel, SessionStatus status, HttpServletRequest request) {
        status.setComplete();
        AuthenticatedLocator locator = new ServletAuthenticatedLocator(request);
        locator.removeAuthenticatedUser();
        request.getSession().invalidate();
        return "index";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "index";
    }
}
