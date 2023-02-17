package com.aduilio.med.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.aduilio.med.dto.DoctorCreateDto;
import com.aduilio.med.dto.DoctorListDto;
import com.aduilio.med.dto.DoctorUpdateDto;
import com.aduilio.med.entity.Doctor;

/**
 * Maps the attributes of a Doctor.
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    /**
     * Maps a DoctorCreateDto to Doctor.
     *
     * @param doctorCreateDto to be mapped
     * @return Doctor
     */
    Doctor mapDoctorFrom(DoctorCreateDto doctorCreateDto);

    /**
     * Maps a DoctorUpdateDto to Doctor.
     *
     * @param doctorUpdateDto to be mapped
     * @param doctor          with the result of the map
     * @return Doctor
     */
    void mapDoctorFrom(DoctorUpdateDto doctorUpdateDto, @MappingTarget Doctor doctor);

    /**
     * Maps a Doctor to DoctorListDto.
     * 
     * @param doctor to be mapped
     * @return DoctorListDto
     */
    DoctorListDto mapDoctorListDtoFrom(Doctor doctor);
}
