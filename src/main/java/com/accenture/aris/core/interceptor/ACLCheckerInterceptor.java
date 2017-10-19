package com.accenture.aris.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.aris.core.acl.ACLChecker;
import com.accenture.aris.core.authorization.AuthorizationData;
import com.accenture.aris.core.authorization.ServletAuthorisedLocator;
import com.accenture.aris.common.mvc.controller.LoginController;

public class ACLCheckerInterceptor implements HandlerInterceptor {

    @Autowired
    ACLChecker aclChecker;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            if(((HandlerMethod)handler).getBean().getClass().equals(LoginController.class)) {
                return true;
            }
        }
        if(isPermitted(request)) return true;
        response.sendRedirect(request.getContextPath() + "/top");
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        return;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {

    }

    private boolean isPermitted(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthorizationData role = (AuthorizationData)session.getAttribute(ServletAuthorisedLocator.DEFAULT_SESSION_ROLE_DATA);
            return aclChecker.aclCheck(role.getRoleId(), request.getServletPath());
        }
        return false;
    }
}
