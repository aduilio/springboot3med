package com.aduilio.med.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * To be thrown when the doctor is not available.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DoctorNotAvailableException extends ServiceException {

    /**
     * Creates a DoctorNotAvailableException.
     */
    public DoctorNotAvailableException() {
        super("The doctor is not available at this time.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
