package com.accenture.aris.core.authentication;

public interface AuthenticatedLocator {

    public String getAuthenicatedUser();
    
    public boolean isAuthenticated();
    
    public boolean saveOrUpadateAuthenticatedUser(AuthenticatorData data);
    
    public boolean removeAuthenticatedUser();
    
    public AuthenticatorData getAuthenticatedData();
}