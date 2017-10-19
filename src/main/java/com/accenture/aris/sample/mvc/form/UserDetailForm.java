package com.accenture.aris.sample.mvc.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserDetailForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String roleId;
    private String email;
    private String sex;
    private String nationality;
    private String defkey;
    private Date startDate;
    private Date endDate;
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.length() < 1){
            this.id = null;
        }
        else{
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() < 1){
            this.name = null;
        }
        else{
            this.name = name;
        }
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        if (roleId == null || roleId.length() < 1){
            this.roleId = null;
        }
        else{
            this.roleId = roleId;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.length() < 1){
            this.email = null;
        }
        else{
            this.email = email;
        }
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if (sex == null || sex.length() < 1){
            this.sex = null;
        }
        else{
            this.sex = sex;
        }
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        if (nationality == null || nationality.length() < 1){
            this.nationality = null;
        }
        else{
            this.nationality = nationality;
        }
    }

    public String getDefkey() {
        return defkey;
    }

    public void setDefkey(String defkey) {
        if (defkey == null || defkey.length() < 1){
            this.defkey = null;
        }
        else{
            this.defkey = defkey;
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        if (text == null || text.length() < 1){
            this.text = null;
        } else {
            this.text=text;
        }
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}