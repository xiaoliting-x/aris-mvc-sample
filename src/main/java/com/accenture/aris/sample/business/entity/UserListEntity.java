package com.accenture.aris.sample.business.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class UserListEntity extends UserEntity{

    private String orgRoleId;
    private String orgSex;
    private String orgNationality;

    public String getOrgRoleId() {
        return orgRoleId;
    }

    public void setOrgRoleId(String orgRoleId) {
        this.orgRoleId = orgRoleId;
    }

    public String getOrgSex() {
        return orgSex;
    }

    public void setOrgSex(String orgSex) {
        this.orgSex = orgSex;
    }

    public String getOrgNationality() {
        return orgNationality;
    }

    public void setOrgNationality(String orgNationality) {
        this.orgNationality = orgNationality;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}