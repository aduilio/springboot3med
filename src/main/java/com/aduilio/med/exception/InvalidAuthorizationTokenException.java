package com.aduilio.med.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * To be thrown when the token is invalid.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthorizationTokenException extends RuntimeException {

    public InvalidAuthorizationTokenException() {
        super("Token invalid or expired.");
    }
}
