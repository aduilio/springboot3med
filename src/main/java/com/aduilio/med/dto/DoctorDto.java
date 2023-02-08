package com.aduilio.med.dto;

import com.aduilio.med.entity.Specialty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

/**
 * Represents a doctor for sending and receiving information.
 */
@Data
@Builder
public class DoctorDto {

	@NotBlank
	private String name;

	@Pattern(regexp = "\\d{4,6}")
	@NotBlank
	private String crm;

	@NotNull
	private Specialty specialty;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String phone;

	@Valid
	@NotNull
	private AddressDto address;
}
