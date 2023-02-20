package com.aduilio.med.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an address for updating information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressUpdateDto {

    private String city;
    private String state;
    private String postalCode;
    private String street;
    private String number;
}
