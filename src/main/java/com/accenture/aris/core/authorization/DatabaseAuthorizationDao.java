package com.accenture.aris.core.authorization;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseAuthorizationDao {

    AuthorizationData selectByName(@Param("name")String name);
}
