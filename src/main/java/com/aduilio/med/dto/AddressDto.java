package com.aduilio.med.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * Represents an address for sending and receiving information.
 */
@Data
@Builder
public class AddressDto {

	@NotBlank
	private String city;

	@NotBlank
	private String state;

	@NotBlank
	private String postalCode;

	@NotBlank
	private String street;

	@NotBlank
	private String number;
}
