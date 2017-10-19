package com.accenture.aris.common.mvc.form;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @NotEmpty
    @Length(max=10)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String name;
    
    @NotEmpty
    @Length(max=16, min=5)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String password;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toString() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this);
        builder.setExcludeFieldNames("password");
        return builder.toString();
    }
}
