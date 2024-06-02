package com.jamsirat.atmapi.exception;

import lombok.*;
import org.springframework.security.core.AuthenticationException;

@Getter
@Setter
public class EmailAlreadyVerifiedException extends AuthenticationException {

    private Integer responseCode;
    private String exceptionMessage;

    public EmailAlreadyVerifiedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public EmailAlreadyVerifiedException(String msg) {
        super(msg);
    }
}
