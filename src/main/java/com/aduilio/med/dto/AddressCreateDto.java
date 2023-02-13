package com.aduilio.med.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an address for sending and receiving information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateDto {

	@NotBlank(message = "{address.city.blank}")
	private String city;

	@NotBlank(message = "{address.state.blank}")
	private String state;

	@NotBlank(message = "{address.postalCode.blank}")
	private String postalCode;

	@NotBlank(message = "{address.street.blank}")
	private String street;

	@NotBlank(message = "{address.number.blank}")
	private String number;
}
