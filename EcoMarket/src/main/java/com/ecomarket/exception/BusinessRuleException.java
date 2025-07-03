package com.ecomarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessRuleException extends RuntimeException {
    private String errorCode;
    private String details;

    public BusinessRuleException(String message) {
        super(message);
    }

    public BusinessRuleException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessRuleException(String message, String errorCode, String details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }

    // Getters
    public String getErrorCode() {
        return errorCode;
    }

    public String getDetails() {
        return details;
    }
}