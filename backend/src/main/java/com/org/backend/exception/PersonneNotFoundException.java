package com.org.backend.exception;

import com.org.backend.dto.CodeError;
import lombok.Getter;

@Getter
public class PersonneNotFoundException extends RuntimeException {
    private final String codeError ;
    public PersonneNotFoundException(CodeError codeError) {
        super(codeError.getMessage());
        this.codeError = codeError.getCode();
    }

}

