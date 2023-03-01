package com.aduilio.med.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.aduilio.med.entity.Address;
import com.aduilio.med.entity.Appointment;
import com.aduilio.med.entity.Appointment.Patient;
import com.aduilio.med.entity.Doctor;
import com.aduilio.med.entity.Specialty;

/**
 * Unit tests for the class {@link AppointmentRepository}.
 */
@DataJpaTest
public class AppointmentRepositoryTest {

    private static final LocalDateTime DATE = LocalDateTime.now();

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void existsByDoctorIdAndDate_withValue_shouldReturnTrue() {
        var doctorId = createAppointment();

        assertThat(appointmentRepository.existsByDoctorIdAndDate(doctorId, DATE)).isTrue();
    }

    private String createAppointment() {
        var address = Address.builder().city("city").state("state").postalCode("12345").street("street").number("123")
                .build();
        var doctor = Doctor.builder().name("name").crm("12345").email("email@email.com").phone("123456")
                .specialty(Specialty.CARDIOLOGY).active(true).address(address).build();
        var doctorId = entityManager.persist(doctor).getId();
        doctor.setId(doctorId);

        var patient = Patient.builder().name("name").phone("phone").build();

        entityManager.persist(Appointment.builder().doctor(doctor).date(DATE).patient(patient).build());

        return doctorId;
    }
}
