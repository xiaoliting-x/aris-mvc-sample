package com.accenture.aris.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.aris.core.authentication.AuthenticatedLocator;
import com.accenture.aris.core.authentication.ServletAuthenticatedLocator;
import com.accenture.aris.common.mvc.controller.LoginController;

public class SessionCheckInterceptor implements HandlerInterceptor {
    
    private static final String DEFAULT_FORWARD_URL = "/sessionTimeout.jsp";
    
    private String forwardUrl = DEFAULT_FORWARD_URL;

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(isLogin(request)) return true;
        if(handler instanceof HandlerMethod) {
            if(((HandlerMethod)handler).getBean().getClass().equals(LoginController.class)) {
                return true;
            }
        }
        if (request.getSession(false) == null && request.getServletPath() != null) {
            request.getRequestDispatcher(forwardUrl).forward(request, response);
            return false;
        }
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        return;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {

    }

    private boolean isLogin(HttpServletRequest request) {
        AuthenticatedLocator locator = new ServletAuthenticatedLocator(request);
        if (!locator.isAuthenticated()) {
            return false;
        }
        return true;
    }
}
