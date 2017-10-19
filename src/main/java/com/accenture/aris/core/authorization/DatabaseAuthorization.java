package com.accenture.aris.core.authorization;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.aris.core.GeneralFailureException;

public class DatabaseAuthorization implements Authorization {

    @Autowired
    private DatabaseAuthorizationDao databaseAuthorizationDao;
    
    @Override
    public AuthorizationData authorize(String name) {
        try {
            return databaseAuthorizationDao.selectByName(name);
        } catch(GeneralFailureException e) {
            throw e;
        }
    }
}
