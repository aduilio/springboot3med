package com.aduilio.med.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Common exception to be extended by custom exceptions.
 */
@Getter
public abstract class ServiceException extends RuntimeException {

    /**
     * Creates a ServiceException.
     * 
     * @param message of the exception
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Returns the http status code.
     * 
     * @return HttpStatus
     */
    public abstract HttpStatus getStatus();
}
