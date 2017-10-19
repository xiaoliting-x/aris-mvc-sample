package com.accenture.aris.core.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ServletAuthorisedLocator implements AuthorisedLocator {

    public static String DEFAULT_SESSION_ROLE_DATA = 
            "ServletAuthenticatedLocator.DEFAULT_SESSION_ROLE_DATA";
    
    private HttpServletRequest request;
    private String sessionRoleData = DEFAULT_SESSION_ROLE_DATA;
    
    public ServletAuthorisedLocator(HttpServletRequest request) {
        this.request = request;
    }

    public String getSessionRoleName() {
        return sessionRoleData;
    }

    public void setSessionRoleName(String sessionRoleName) {
        this.sessionRoleData = sessionRoleName;
    }

    @Override
    public String getAuthorisedRole() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String)session.getAttribute(this.sessionRoleData);
        }
        return null;
    }
    
    @Override
    public boolean isAuthorised() {
        if (getAuthorisedRole() != null) {
            return true;
        }
        return false;
    }

    public String getRemoteUser() {
        return request.getRemoteUser();
    }

    @Override
    public boolean saveOrUpadateAuthorisedRole(AuthorizationData data) {
        if (data != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(sessionRoleData, data);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean removeAuthorisedRole() {
        if (isAuthorised() == true) {
            HttpSession session = request.getSession();
            session.removeAttribute(sessionRoleData);
            return true;
        }
        return false;
    }

    @Override
    public AuthorizationData getAuthorizationData() {
        HttpSession session = request.getSession(true);
        return (AuthorizationData)session.getAttribute(sessionRoleData);
    }
    
}
