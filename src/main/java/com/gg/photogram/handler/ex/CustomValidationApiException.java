package com.gg.photogram.handler.ex;

import java.util.HashMap;
import java.util.Map;

public class CustomValidationApiException extends RuntimeException {

    Map<String, String> errorMap = new HashMap<>();

    public CustomValidationApiException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
