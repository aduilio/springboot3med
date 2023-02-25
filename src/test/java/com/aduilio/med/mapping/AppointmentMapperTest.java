package com.aduilio.med.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aduilio.med.dto.AppointmentCreateDto;
import com.aduilio.med.entity.Appointment;
import com.aduilio.med.entity.Doctor;

/**
 * Unit tests for {@link AppointmentMapper}
 */
@ExtendWith(MockitoExtension.class)
public class AppointmentMapperTest {

    private static final LocalDateTime DATE = LocalDateTime.now();
    private static final Long DOCTOR_ID = 1L;
    private static final String PATIENT_NAME = "name";
    private static final String PATIENT_PHONE = "phone";

    @Test
    void mapAppointmentFrom_withDoctorCreateDto_shouldReturnDoctor() {
        var doctor = Doctor.builder().id(DOCTOR_ID.toString()).build();
        var patient = Appointment.Patient.builder().name(PATIENT_NAME).phone(PATIENT_PHONE).build();

        var appointment = Appointment.builder().doctor(doctor).patient(patient).date(DATE).build();

        var result = AppointmentMapper.INSTANCE.mapAppointmentReadDtoFrom(appointment);

        assertEquals(result.getDoctor().getId(), DOCTOR_ID);
        assertEquals(result.getPatient().getName(), PATIENT_NAME);
        assertEquals(result.getPatient().getPhone(), PATIENT_PHONE);
        assertEquals(result.getDate(), DATE);
    }

    @Test
    void mapDoctorFrom_withDoctorCreateDto_shouldReturnDoctor() {
        var doctor = AppointmentCreateDto.AppointmentDoctorDto.builder().id(DOCTOR_ID).build();
        var patient = AppointmentCreateDto.AppointmentPatientDto.builder().name(PATIENT_NAME).phone(PATIENT_PHONE)
                .build();

        var appointmentDto = AppointmentCreateDto.builder().doctor(doctor).patient(patient).date(DATE).build();

        var result = AppointmentMapper.INSTANCE.mapAppointmentFrom(appointmentDto);

        assertEquals(result.getDoctor().getId(), DOCTOR_ID.toString());
        assertEquals(result.getPatient().getName(), PATIENT_NAME);
        assertEquals(result.getPatient().getPhone(), PATIENT_PHONE);
        assertEquals(result.getDate(), DATE);
    }
}
