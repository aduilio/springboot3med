package com.aduilio.med.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aduilio.med.entity.Appointment;

/**
 * Provides the methods to persist an Appointment.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

}
