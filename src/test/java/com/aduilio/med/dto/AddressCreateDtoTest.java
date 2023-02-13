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
 * Unit tests for {@link AddressCreateDto}
 */
@ExtendWith(MockitoExtension.class)
public class AddressCreateDtoTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validate_missingAllMandatoryAttributtes_shouldReturnViolations() {
        Set<ConstraintViolation<AddressCreateDto>> violations = validator.validate(new AddressCreateDto());

        assertThat(violations).hasSize(5);

        assertThat(violations.stream().map(ConstraintViolation::getMessage)).containsExactlyInAnyOrder(
                "{address.city.blank}", "{address.state.blank}", "{address.postalCode.blank}", "{address.street.blank}",
                "{address.number.blank}");
    }
}
