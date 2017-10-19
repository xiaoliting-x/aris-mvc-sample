package com.accenture.aris.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.aris.core.authentication.AuthenticatedLocator;
import com.accenture.aris.core.authentication.ServletAuthenticatedLocator;
import com.accenture.aris.core.menu.MenuRegister;

public class MenuResistInterceptor implements HandlerInterceptor {

    @Autowired
    private MenuRegister menuRegister;
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
    
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        injectIntoMenuAwareAction(request, handler, modelAndView);
    }
    
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        
    }
    
    private void injectIntoMenuAwareAction(HttpServletRequest request, Object handler, ModelAndView modelAndView) {
        try {
            AuthenticatedLocator locator = new ServletAuthenticatedLocator(request);
            if (locator.isAuthenticated()) {
                HttpSession session = request.getSession();
                if(session.getAttribute("menu") == null && modelAndView != null) {
                    menuRegister.regist(locator.getAuthenicatedUser(), modelAndView);
                }
            }           
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}