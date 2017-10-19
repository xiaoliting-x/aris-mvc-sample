package com.accenture.aris.core.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ServletAuthenticatedLocator implements AuthenticatedLocator {

    public static String DEFAULT_SESSION_USER_DATA = 
            "ServletAuthenticatedLocator.DEFAULT_SESSION_USER_DATA";
    
    private HttpServletRequest request;
    private String sessionUserData = DEFAULT_SESSION_USER_DATA;
    
    public ServletAuthenticatedLocator(HttpServletRequest request) {
        this.request = request;
    }

    public String getSessionUserName() {
        return sessionUserData;
    }

    public void setSessionUserName(String sessionUserName) {
        this.sessionUserData = sessionUserName;
    }

    public String getAuthenicatedUser() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthenticatorData data = (AuthenticatorData)session.getAttribute(this.sessionUserData);
            if (data != null) {
                return data.getId();
            }
        }
        return null;
    }
    
    public boolean isAuthenticated() {
        if (getAuthenicatedUser() != null) {
            return true;
        }
        return false;
    }

    public String getRemoteUser() {
        return request.getRemoteUser();
    }

    public boolean saveOrUpadateAuthenticatedUser(AuthenticatorData data) {
        if (data != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(sessionUserData, data);
            return true;
        }
        return false;
    }
    
    public boolean removeAuthenticatedUser() {
        if (isAuthenticated() == true) {
            HttpSession session = request.getSession();
            session.removeAttribute(sessionUserData);
            return true;
        }
        return false;
    }

    @Override
    public AuthenticatorData getAuthenticatedData() {
        HttpSession session = request.getSession(true);
        return (AuthenticatorData)session.getAttribute(sessionUserData);
    }
}
