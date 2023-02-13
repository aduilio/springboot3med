package com.aduilio.med.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.aduilio.med.dto.DoctorCreateDto;
import com.aduilio.med.entity.Doctor;

/**
 * Maps the attributes of a Doctor.
 */
@Mapper
public interface DoctorMapper {

	DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

	/**
	 * Maps a DoctorDto to Doctor.
	 *
	 * @param doctorDto to be mapped
	 *
	 * @return Doctor
	 */
	Doctor mapDoctorFrom(DoctorCreateDto doctorCreateDto);
}
