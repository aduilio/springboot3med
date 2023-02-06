package com.aduilio.med.dto;

import com.aduilio.med.entity.Specialty;

import lombok.Builder;
import lombok.Data;

/**
 * Represents a doctor for sending and receiving information.
 */
@Data
@Builder
public class DoctorDto {

	private String name;
	private String crm;
	private Specialty specialty;
	private String email;
	private String phone;
	private AddressDto address;
}
