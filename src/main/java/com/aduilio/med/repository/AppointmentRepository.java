package com.aduilio.med.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aduilio.med.entity.Appointment;

/**
 * Provides the methods to persist an Appointment.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    /**
     * Checks if a doctor has an appointment in a specific date.
     * 
     * @param doctorId the doctor id
     * @param date     the appointment date
     * @return boolean
     */
    boolean existsByDoctorIdAndDate(String doctorId, LocalDateTime date);
}
