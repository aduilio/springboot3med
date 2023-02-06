package com.aduilio.med.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Represents an address for sending and receiving information.
 */
@Data
@Builder
public class AddressDto {

	private String city;
	private String postalCode;
	private String street;
	private String number;
}
