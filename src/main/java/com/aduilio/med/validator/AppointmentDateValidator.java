package com.aduilio.med.validator;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.aduilio.med.dto.AppointmentCreateDto;
import com.aduilio.med.exception.InvalidAppointmentDateException;

/**
 * Validates if the appointment date is valid.
 */
@Component
public class AppointmentDateValidator implements AppointmentValidator {

    /**
     * Validates if the appointment date has a valid hour (7 am to 7 pm), valid day
     * (not Sunday) and it is 30 minutes in advance.
     * 
     * @param appointmentCreateDto from the request
     */
    @Override
    public void validate(AppointmentCreateDto appointmentCreateDto) {
        var date = appointmentCreateDto.getDate();

        if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY) || (date.getHour() < 7 || date.getHour() > 18)) {
            throw new InvalidAppointmentDateException(InvalidAppointmentDateException.INVALID_HOUR);
        }

        if (Duration.between(LocalDateTime.now(), date).toMinutes() < 30) {
            throw new InvalidAppointmentDateException(InvalidAppointmentDateException.INVALID_SCHEDULE);
        }
    }
}
