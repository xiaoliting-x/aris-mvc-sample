package com.accenture.aris.core.acl;

import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseACLChecker implements ACLChecker {
    
    @Autowired
    private ACLCheckerDao aclCheckerDao;
    
    @Override
    public boolean aclCheck(String id, String url) {
        ACLCheckerData data = aclCheckerDao.selectByIdAndUrl(id, url);
        if(data == null) {
            return false;
        }
        return true;
    }
}