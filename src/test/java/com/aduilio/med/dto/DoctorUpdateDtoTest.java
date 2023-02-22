package com.aduilio.med.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

/**
 * Unit tests for {@link DoctorUpdateDto}
 */
@ExtendWith(MockitoExtension.class)
public class DoctorUpdateDtoTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validate_invalidEmail_shouldReturnViolations() {
        var doctorUpdateDto = DoctorUpdateDto.builder().email("email").build();

        Set<ConstraintViolation<DoctorUpdateDto>> violations = validator.validate(doctorUpdateDto);

        assertThat(violations).hasSize(1);

        assertThat(violations.stream().map(ConstraintViolation::getMessage)).containsExactly("{doctor.email.invalid}");
    }
}
