package com.org.backend.exception;

import com.org.backend.dto.CodeError;

public class ProductNotFoundException extends RuntimeException {
    private final String errorCode ;
    public ProductNotFoundException(CodeError codeError) {
        super(codeError.getMessage());
        this.errorCode = codeError.getCode();
    }
}

