package com.accenture.aris.core.acl;

import java.io.Serializable;

public class ACLCheckerData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String url;
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUrl() {
        return url;
    }
}
