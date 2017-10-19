package com.accenture.aris.sample.mvc.form;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

public class UserUploadForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    
    private MultipartFile uploadFile;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public MultipartFile getUploadFile() {
        return uploadFile;
    }
    
    public void setUploadFile(MultipartFile multipartFile) {
        this.uploadFile = multipartFile;
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}