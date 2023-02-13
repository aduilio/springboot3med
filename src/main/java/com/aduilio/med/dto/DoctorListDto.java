package com.aduilio.med.dto;

import com.aduilio.med.entity.Specialty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the data for listing doctors.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorListDto {

    private String id;
    private String name;
    private String crm;
    private Specialty specialty;
}
