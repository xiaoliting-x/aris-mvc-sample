package com.accenture.aris.core.authentication;

public interface Authenticator {

    public AuthenticatorData authenticate(String username, String password);
}
