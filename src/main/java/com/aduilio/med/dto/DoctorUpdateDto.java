package com.aduilio.med.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the data for updating a doctor.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorUpdateDto {

    private String name;

    @Email(message = "{doctor.email.invalid}")
    private String email;

    private String phone;

    @Valid
    private AddressUpdateDto address;
}
