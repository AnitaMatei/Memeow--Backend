package com.callbackcats.memeow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TemplateNotFoundException extends Exception {
    public TemplateNotFoundException(String message) {
        super(message);
    }
}
