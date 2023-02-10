package com.aduilio.med.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aduilio.med.dto.AddressDto;
import com.aduilio.med.dto.DoctorDto;
import com.aduilio.med.entity.Doctor;
import com.aduilio.med.entity.Specialty;

/**
 * Unit tests for {@link DoctorMapper}
 */
@ExtendWith(MockitoExtension.class)
public class DoctorMapperTest {

    private static final String NAME = "name";
    private static final String CRM = "crm";
    private static final Specialty SPECIALTY = Specialty.CARDIOLOGY;
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String POSTAL_CODE = "postal_code";
    private static final String STREET = "street";
    private static final String NUMBER = "number";

    @Test
    void mapDoctorFrom_withDoctorDto_shouldReturnDoctor() {
        AddressDto addressDto = AddressDto.builder()
            .city(CITY)
            .state(STATE)
            .postalCode(POSTAL_CODE)
            .street(STREET)
            .number(NUMBER)
            .build();

        DoctorDto doctorDto = DoctorDto.builder()
            .name(NAME)
            .crm(CRM)
            .specialty(SPECIALTY)
            .email(EMAIL)
            .phone(PHONE)
            .address(addressDto)
            .build();

        Doctor result = DoctorMapper.INSTANCE.mapDoctorFrom(doctorDto);

        assertEquals(result.getName(), NAME);
        assertEquals(result.getCrm(), CRM);
        assertEquals(result.getSpecialty(), SPECIALTY);
        assertEquals(result.getEmail(), EMAIL);
        assertEquals(result.getPhone(), PHONE);
        assertEquals(result.getAddress().getCity(), CITY);
        assertEquals(result.getAddress().getState(), STATE);
        assertEquals(result.getAddress().getPostalCode(), POSTAL_CODE);
        assertEquals(result.getAddress().getStreet(), STREET);
        assertEquals(result.getAddress().getNumber(), NUMBER);
    }
}
