
package com.aduilio.med.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aduilio.med.dto.AppointmentCreateDto;
import com.aduilio.med.exception.DoctorNotAvailableException;
import com.aduilio.med.exception.DoctorNotFoundException;
import com.aduilio.med.repository.AppointmentRepository;
import com.aduilio.med.repository.DoctorRepository;

/**
 * Validates if the appointment doctor is valid.
 */
@Component
public class AppointmentDoctorValidator implements AppointmentValidator {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Validates if the appointment doctor exists, it is active and available.
     * 
     * @param appointmentCreateDto from the request
     */
    @Override
    public void validate(AppointmentCreateDto appointmentCreateDto) {
        var doctorId = appointmentCreateDto.getDoctor().getId().toString();
       
        if (!doctorRepository.existsById(doctorId) || !doctorRepository.isActive(doctorId)) {
            throw new DoctorNotFoundException(doctorId.toString());
        }

        if (appointmentRepository.existsByDoctorIdAndDate(doctorId, appointmentCreateDto.getDate())) {
            throw new DoctorNotAvailableException();
        }
    }
}
