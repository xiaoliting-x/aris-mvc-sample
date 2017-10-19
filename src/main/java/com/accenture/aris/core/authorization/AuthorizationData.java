package com.accenture.aris.core.authorization;

import java.io.Serializable;

public class AuthorizationData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String roleId;
    private String roleName;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleId() {
        return roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleName() {
        return roleName;
    }
}
