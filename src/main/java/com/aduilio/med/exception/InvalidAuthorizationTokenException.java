package com.aduilio.med.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * To be thrown when the token is invalid.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthorizationTokenException extends ServiceException {

    /**
     * Creates an InvalidAuthorizationTokenException.
     */
    public InvalidAuthorizationTokenException() {
        super("Token invalid or expired.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
