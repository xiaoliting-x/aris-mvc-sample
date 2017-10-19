package com.accenture.aris.common.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonDateSerializer extends JsonSerializer<Date> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDateSerializer.class);

    public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";
    public static final String DATE_FORMAT_PROPERTY_KEY = "json.date.format";
    
    private String dateFormat = DEFAULT_DATE_FORMAT;
    
    public JsonDateSerializer() {
        String format = System.getProperty(DATE_FORMAT_PROPERTY_KEY);
        
        LOGGER.debug("json.date.format = {}", format);
        
        if (format != null && format.length() > 0) {
            this.dateFormat = format;
        }
    }
    
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public void serialize(Date value, JsonGenerator generator, SerializerProvider provider) 
            throws IOException, JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        generator.writeString(formatter.format(value));
    }
}
