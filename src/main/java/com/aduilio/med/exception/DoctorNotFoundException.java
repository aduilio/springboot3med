package com.aduilio.med.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * To be thrown when the doctor id is invalid.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DoctorNotFoundException extends ServiceException {

    /**
     * Creates a DoctorNotFoundException.
     * 
     * @param id the doctor id
     */
    public DoctorNotFoundException(final String id) {
        super(String.format("Doctor %s not found.", id));
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
