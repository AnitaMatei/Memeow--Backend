package com.callbackcats.memeow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadImageException extends Exception {
    public BadImageException(String message) {
        super(message);
    }
}
