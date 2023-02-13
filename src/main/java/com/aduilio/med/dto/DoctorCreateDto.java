package com.aduilio.med.dto;

import com.aduilio.med.entity.Specialty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the data for creating a doctor.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorCreateDto {

    @NotBlank(message = "{doctor.name.blank}")
    private String name;

    @Pattern(regexp = "\\d{4,6}", message = "{doctor.crm.invalid}")
    @NotBlank(message = "{doctor.crm.blank}")
    private String crm;

    @NotNull(message = "{doctor.specialty.blank}")
    private Specialty specialty;

    @Email(message = "{doctor.email.invalid}")
    @NotBlank(message = "{doctor.email.blank}")
    private String email;

    @NotBlank(message = "{doctor.phone.blank}")
    private String phone;

    @Valid
    @NotNull(message = "{doctor.address.blank}")
    private AddressCreateDto address;
}
