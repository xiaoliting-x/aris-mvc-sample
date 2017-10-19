package com.accenture.aris.core.menu;

import java.io.Serializable;

public class MenuRegisterData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private String name;
    
    private String content;
    
    private String url;
    
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUrl() {
        return url;
    }
}
