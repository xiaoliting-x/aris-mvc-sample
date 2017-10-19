package com.accenture.aris.core.support.utils;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ModelAttributesUtils {

    public static final String ERROR_VALUE_SUFFIX = "_errorValue";
    
    public static final String ERROR_MESSAGE_SUFFIX = "_errorMessage";
    
    public static final String ERROR_CSS_SUFFIX = "_errorCss";
    
    public static void addErrorAttributes(BindingResult result, Model uiModel) {
        if (result.hasErrors() == false) {
            return;
        }
        
        for (FieldError error: result.getFieldErrors()) {
            String fieldName = error.getField();
            uiModel.addAttribute(fieldName + ERROR_VALUE_SUFFIX, error.getRejectedValue());
            uiModel.addAttribute(fieldName + ERROR_MESSAGE_SUFFIX, error.getDefaultMessage());
            uiModel.addAttribute(fieldName + ERROR_CSS_SUFFIX, "error");
        }
    }
}
