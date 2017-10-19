package com.accenture.aris.core.support.codeloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.accenture.aris.core.GeneralFailureException;

@Component
public class StaticCodeLoader {

    private static Logger LOGGER = LoggerFactory.getLogger(StaticCodeLoader.class);

    private static final String DEFAULT_FILENAME = "codeList.xml";

    private Map<String, List<CodeData>> codeListMap = null;
    private Map<String, CodeData> codeMap = null;
    private String filename = DEFAULT_FILENAME;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @PostConstruct
    public void initialize() throws Exception {
        // get input stream
        InputStream is = null;
        try {
            ClassLoader loader =Thread.currentThread().getContextClassLoader();
            is = loader.getResourceAsStream(filename);
            if (is == null) {
                is = new FileInputStream(filename);
            }
        } catch (FileNotFoundException ex) {
            throw new GeneralFailureException("file = [" + filename + "] is not exists.", ex);
        }

        // get document
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(is);

        // load master code
        List<Element> categories = doc.getRootElement().getChildren("category");
        Map<String, List<CodeData>> newListMap = new HashMap<String, List<CodeData>>();
        Map<String, CodeData> newCodeMap = new HashMap<String, CodeData>();
        for(Element category: categories) {
            String name = category.getAttributeValue("name");
            List<Element> codes = category.getChildren("codeData");
            List<CodeData> codeList = new ArrayList<CodeData>();
            for(Element code: codes) {
                CodeData codeData = new CodeData();
                codeData.setCategory(name);
                codeData.setCode(code.getAttributeValue("code"));
                codeData.setData(code.getAttributeValue("data"));
                codeList.add(codeData);
                newCodeMap.put(name + "." + code.getAttributeValue("code"), codeData);
            }
            newListMap.put(name, codeList);
        }
        this.codeListMap = newListMap;
        this.codeMap = newCodeMap;

        LOGGER.info("Initialize complate. codeList={}, codeMap={}.", this.codeListMap, this.codeMap);
    }

    public List<CodeData> getCodeDataList(String category) {
        if (codeListMap.containsKey(category) == false) {
            throw new GeneralFailureException("Category=[" + category + "] is not exists.");
        }
        List<CodeData> oldList = this.codeListMap.get(category);
        List<CodeData> newList = new ArrayList<CodeData>(oldList.size());
        newList.addAll(oldList);
        return newList;
    }

    public CodeData getCodeData(String category, String code) {
        String path = category + "." + code;
        if (this.codeMap.containsKey(path) == false) {
            throw new GeneralFailureException("Category.Code=[" + path + "] is not exists.");
        }
        return this.codeMap.get(path);
    }

    public String getCodeDataValue(String category, String code) {
        return getCodeData(category, code).getData();
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
