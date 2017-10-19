package com.accenture.aris.core.authorization;

public interface AuthorisedLocator {

    public String getAuthorisedRole();
    
    public boolean isAuthorised();
    
    public boolean saveOrUpadateAuthorisedRole(AuthorizationData data);
    
    public boolean removeAuthorisedRole();
    
    public AuthorizationData getAuthorizationData();
}