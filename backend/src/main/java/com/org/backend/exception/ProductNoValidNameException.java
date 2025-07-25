package com.org.backend.exception;

import com.org.backend.dto.CodeError;
import lombok.Getter;

@Getter
public class ProductNoValidNameException extends RuntimeException {
    private final String errorCode;

    public ProductNoValidNameException(CodeError codeError ) {
        super(codeError.getMessage());
        this.errorCode = codeError.getCode();
    }

    public ProductNoValidNameException(CodeError codeError, String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}