package com.aduilio.med.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * To be thrown when the request does not have the Authorization header.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingAuthorizationHeaderException extends RuntimeException {

    public MissingAuthorizationHeaderException() {
        super("Authorization header is missing.");
    }
}
