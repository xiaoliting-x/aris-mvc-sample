package com.accenture.aris.core.authentication;

import java.io.Serializable;

public class AuthenticatorData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String password;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
