package com.accenture.aris.core.authentication;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticatorDao {

    AuthenticatorData selectByNameAndPassword(@Param("name")String name, @Param("password")String password);    
}
