package com.accenture.aris.core.support.pagination;

public class Pagination {

    String content;
    String cssClass;
    int index;
    
    public Pagination(int index, String content, String cssClass) {
        this.content = content;
        this.index = index;
        this.cssClass = cssClass;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getCssClass() {
        return cssClass;
    }
    
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
}
