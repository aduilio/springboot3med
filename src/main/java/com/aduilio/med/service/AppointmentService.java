package com.aduilio.med.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aduilio.med.dto.AppointmentCreateDto;
import com.aduilio.med.entity.Appointment;
import com.aduilio.med.mapping.AppointmentMapper;
import com.aduilio.med.repository.AppointmentRepository;

/**
 * Provides the validations to schedule an appointment.
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private AppointmentMapper appointmentMapper = AppointmentMapper.INSTANCE;

    /**
     * Validate and schedule a new appointment.
     * 
     * @param appointment the appointment information
     * @return Appointment
     */
    public Appointment schedule(AppointmentCreateDto appointmentDto) {
        var appointment = appointmentMapper.mapAppointmentFrom(appointmentDto);
        return appointmentRepository.save(appointment);
    }

    /**
     * Validate and schedule a new appointment.
     * 
     * @param pageable with pagination information
     * @Return Page of Appointment
     */
    public Page<Appointment> list(final Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }
}
