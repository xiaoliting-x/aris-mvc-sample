package com.accenture.aris.core.menu;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRegisterDao {

    List<MenuRegisterData> selectByUserId(@Param("userId")String userId);
    
}
