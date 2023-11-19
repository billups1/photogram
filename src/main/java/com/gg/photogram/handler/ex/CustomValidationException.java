package com.gg.photogram.handler.ex;

import java.util.HashMap;
import java.util.Map;

public class CustomValidationException extends RuntimeException {

    Map<String, String> errorMap = new HashMap<>();

    public CustomValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public CustomValidationException(String message) {
        super(message);
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
