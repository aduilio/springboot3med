package com.aduilio.med.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * To be thrown when the doctor id is invalid.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DoctorNotFoundException extends RuntimeException {
    
    public DoctorNotFoundException(final String id) {
        super(String.format("Doctor %s not found.", id));
    }
}
