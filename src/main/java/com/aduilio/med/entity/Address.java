package com.aduilio.med.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents an address to persist the information.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

	private String city;
	private String state;
	private String postalCode;
	private String street;
	private String number;
}