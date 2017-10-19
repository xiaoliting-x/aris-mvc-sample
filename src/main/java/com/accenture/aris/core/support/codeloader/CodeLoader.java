package com.accenture.aris.core.support.codeloader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeLoader {

    @Autowired
    private CodeDao codeDao;

    public CodeData getCodeData(String category, String code) {
        assert(category != null);
        assert(code != null);
        return codeDao.selectByCategoryAndCode(category, code);
    }

    public String getCodeDataValue(String category, String code) {
        return getCodeData(category, code).getData();
    }

    public List<CodeData>getCodeDataList(String category ) {
        return  codeDao.selectByCategory(category);
    }
}
