package com.accenture.aris.common.json;

import java.io.IOException;
import java.text.DecimalFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonNumberSerializer extends JsonSerializer<Number> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonNumberSerializer.class);
    
    private static final String DEFAULT_NUMBER_FORMAT = "################0.0###";
    public static final String NUMBER_FORMAT_PROPERTY_KEY = "json.number.format";
    
    private String numberFormat = DEFAULT_NUMBER_FORMAT;
    
    public JsonNumberSerializer() {
        String format = System.getProperty(NUMBER_FORMAT_PROPERTY_KEY);
        
        LOGGER.debug("json.number.format = {}", format);
        
        if (format != null && format.length() > 0) {
            this.numberFormat = format;
        }    }
    
    public void setNumberFormat(String numberFormat) {
        this.numberFormat = numberFormat;
    }

    @Override
    public void serialize(Number value, JsonGenerator generator, SerializerProvider provider) 
            throws IOException, JsonProcessingException {
        DecimalFormat formatter = new DecimalFormat(numberFormat);
        generator.writeString(formatter.format(value));
    }

}
