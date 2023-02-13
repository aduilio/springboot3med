package com.aduilio.med.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aduilio.med.entity.Specialty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

/**
 * Unit tests for {@link DoctorCreateDto}
 */
@ExtendWith(MockitoExtension.class)
public class DoctorCreateDtoTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validate_missingAllMandatoryAttributtes_shouldReturnViolations() {
        Set<ConstraintViolation<DoctorCreateDto>> violations = validator.validate(new DoctorCreateDto());

        assertThat(violations).hasSize(6);

        assertThat(violations.stream().map(ConstraintViolation::getMessage)).containsExactlyInAnyOrder(
                "{doctor.name.blank}", "{doctor.phone.blank}", "{doctor.crm.blank}", "{doctor.address.blank}",
                "{doctor.specialty.blank}", "{doctor.email.blank}");
    }

    @Test
    void validate_invalidCrmEmail_shouldReturnViolations() {
        DoctorCreateDto doctorCreateDto = DoctorCreateDto.builder().name("name").crm("crm").email("email")
                .specialty(Specialty.CARDIOLOGY).phone("phone").address(AddressCreateDto.builder().city("city")
                        .state("state").postalCode("pc").street("st").number("n").build())
                .build();

        Set<ConstraintViolation<DoctorCreateDto>> violations = validator.validate(doctorCreateDto);

        assertThat(violations).hasSize(2);

        assertThat(violations.stream().map(ConstraintViolation::getMessage))
                .containsExactlyInAnyOrder("{doctor.crm.invalid}", "{doctor.email.invalid}");
    }
}
