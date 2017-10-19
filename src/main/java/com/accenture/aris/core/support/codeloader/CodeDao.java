package com.accenture.aris.core.support.codeloader;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeDao {
    
    List<CodeData> selectByCategory(String category);
    
    CodeData selectByCategoryAndCode(@Param("category")String category, @Param("code")String code);
}