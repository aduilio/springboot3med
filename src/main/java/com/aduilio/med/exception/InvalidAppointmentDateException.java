package com.aduilio.med.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * To be thrown when the appointment date is invalid.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAppointmentDateException extends ServiceException {

    public static final String INVALID_HOUR = "The appointment date must be from Monday to Saturday from 7 am to 7 pm.";

    public static final String INVALID_SCHEDULE = "The appointment must be scheduled at least 30 minutes in advance.";

    /**
     * Creates an InvalidAppointmentDateException.
     * 
     * @param message the exception reason
     */
    public InvalidAppointmentDateException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
