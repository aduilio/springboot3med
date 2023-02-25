package com.aduilio.med.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.aduilio.med.dto.AppointmentCreateDto;
import com.aduilio.med.dto.AppointmentReadDto;
import com.aduilio.med.entity.Appointment;

/**
 * Maps the attributes of an Appointment.
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    /**
     * Maps a AppointmentCreateDto to Appointment.
     * 
     * @param appointmentDto to be mapped
     * @return Appointment
     */
    Appointment mapAppointmentFrom(AppointmentCreateDto appointmentDto);

    /**
     * Maps a Appointment to AppointmentReadDto.
     * 
     * @param appointment to be mapped
     * @return AppointmentReadDto
     */
    AppointmentReadDto mapAppointmentReadDtoFrom(Appointment appointment);
}
