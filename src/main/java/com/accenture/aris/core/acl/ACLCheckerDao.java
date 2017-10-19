package com.accenture.aris.core.acl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ACLCheckerDao {

    ACLCheckerData selectByIdAndUrl(@Param("id")String id, @Param("url")String url);    
}
