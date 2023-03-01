package com.aduilio.med.validator;

import com.aduilio.med.dto.AppointmentCreateDto;

/**
 * Validates an appointment.
 */
public interface AppointmentValidator {

    /**
     * Validates an appointment.
     * 
     * @param appointmentCreateDto from the request
     */
    void validate(AppointmentCreateDto appointment);
}
