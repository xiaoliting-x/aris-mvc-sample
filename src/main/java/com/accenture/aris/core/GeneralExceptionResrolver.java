package com.accenture.aris.core;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.aris.core.authentication.AuthenticatedLocator;
import com.accenture.aris.core.authentication.ServletAuthenticatedLocator;
import com.accenture.aris.core.authorization.AuthorisedLocator;
import com.accenture.aris.core.authorization.ServletAuthorisedLocator;

public class GeneralExceptionResrolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerExceptionResolver.class);
    
    private boolean isProductMode = true;

    private String errorScreen = "error";
    
    public void setProductMode(boolean isProductMode) {
        this.isProductMode = isProductMode;
    }

    public void setErrorScreen(String errorScreen) {
        this.errorScreen = errorScreen;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object value, Exception ex) {
        LOGGER.error("catch exception.", ex);
        ModelAndView mv = new ModelAndView();
        if (isProductMode == false) {
            AuthenticatedLocator userLocator = new ServletAuthenticatedLocator(request);
            mv.addObject("user", userLocator.getAuthenicatedUser());
            AuthorisedLocator roleLocator = new ServletAuthorisedLocator(request);
            mv.addObject("role", roleLocator.getAuthorisedRole());
            mv.addObject("url", request.getRequestURI());
            mv.addObject("contentType", request.getContentType() == null ? "-----" : request.getContentType());
            mv.addObject("contentLength", request.getContentLength());
            mv.addObject("sessionId", request.getRequestedSessionId());
            
            // Cookie
            StringBuffer cookies = new StringBuffer();
            for (Cookie cokkie: request.getCookies()) {
                cookies.append(cokkie.getName()).append("=").append(cokkie.getValue()).append("; ");
            }
            mv.addObject("cookies", cookies);
            
            // HTTP Header
            Enumeration<String> headerNames = request.getHeaderNames();
            StringBuffer headers = new StringBuffer();
            while(headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                headers.append(name).append("=").append(request.getHeader(name)).append("; ");
            }
            mv.addObject("headers", headers);
            
            // Request Parameters
            Enumeration<String> paramNames = request.getParameterNames();
            StringBuffer params = new StringBuffer();
            while(paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();
                params.append(name).append("=").append(request.getParameter(name)).append("; ");
            }
            mv.addObject("parameters", params);
            
            // exception list
            List<String > exceptions = new ArrayList<String>();
            for (StackTraceElement ele: ex.getStackTrace()) {
                exceptions.add(ele.toString());
            }
            mv.addObject("exceptionName", ex.getClass().getName());
            mv.addObject("exceptions", exceptions);
        }
        
        // 例外をキャッチしたらセッションを破棄する
        request.getSession().invalidate();
        
        mv.setViewName(errorScreen);
        return mv;
    }
}
