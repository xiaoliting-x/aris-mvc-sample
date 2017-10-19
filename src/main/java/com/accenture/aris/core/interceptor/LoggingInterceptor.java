package com.accenture.aris.core.interceptor;

//import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        if (handler instanceof HandlerMethod) {
            LOGGER.debug(((HandlerMethod)handler).getMethod().getName() + " started.");
        } else {
            LOGGER.debug("failure. handler is not instanceof HandlerMethod.");
        }
        return true;
    }
    
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        return;
    }
    
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        if(exception != null) {
            LOGGER.error(((HandlerMethod)handler).getMethod().getName() + " error occured.");
            LOGGER.error(exception.getMessage());
            exception.printStackTrace();
        }
        if (handler instanceof HandlerMethod) {
            LOGGER.debug(((HandlerMethod)handler).getMethod().getName() + " ended.");
        }
        
//      for (Enumeration<String> e = request.getAttributeNames(); e.hasMoreElements(); ) {
//          String name = e.nextElement();
//          LOGGER.debug("Name=[{}] Value=[{}]", name, request.getAttribute(name));
//      }
    }
}
