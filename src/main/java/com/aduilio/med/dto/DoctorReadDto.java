package com.aduilio.med.dto;

import com.aduilio.med.entity.Address;
import com.aduilio.med.entity.Specialty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a doctor to persist the information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorReadDto {

    private String id;
    private String name;
    private String crm;
    private Specialty specialty;
    private String email;
    private String phone;
    private Address address;
}
