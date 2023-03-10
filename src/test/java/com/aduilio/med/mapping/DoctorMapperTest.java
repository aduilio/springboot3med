package com.aduilio.med.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aduilio.med.dto.AddressCreateDto;
import com.aduilio.med.dto.AddressUpdateDto;
import com.aduilio.med.dto.DoctorCreateDto;
import com.aduilio.med.dto.DoctorListDto;
import com.aduilio.med.dto.DoctorUpdateDto;
import com.aduilio.med.entity.Address;
import com.aduilio.med.entity.Doctor;
import com.aduilio.med.entity.Specialty;

/**
 * Unit tests for {@link DoctorMapper}
 */
@ExtendWith(MockitoExtension.class)
public class DoctorMapperTest {

    private static final String ID = "id";
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
    void mapDoctorFrom_withDoctorCreateDto_shouldReturnDoctor() {
        var addressDto = AddressCreateDto.builder().city(CITY).state(STATE).postalCode(POSTAL_CODE).street(STREET)
                .number(NUMBER).build();

        var doctorDto = DoctorCreateDto.builder().name(NAME).crm(CRM).specialty(SPECIALTY).email(EMAIL).phone(PHONE)
                .address(addressDto).build();

        var result = DoctorMapper.INSTANCE.mapDoctorFrom(doctorDto);

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

    @Test
    void mapDoctorFrom_withDoctorUpdateDto_shouldReturnDoctor() {
        var addressDto = AddressUpdateDto.builder().city(CITY).state(STATE).postalCode(POSTAL_CODE).street(STREET)
                .number(NUMBER).build();

        var doctorDto = DoctorUpdateDto.builder().name(NAME).email(EMAIL).phone(PHONE).address(addressDto).build();
        var result = new Doctor();

        DoctorMapper.INSTANCE.mapDoctorFrom(doctorDto, result);

        assertEquals(result.getName(), NAME);
        assertEquals(result.getCrm(), null);
        assertEquals(result.getSpecialty(), null);
        assertEquals(result.getEmail(), EMAIL);
        assertEquals(result.getPhone(), PHONE);
        assertEquals(result.getAddress().getCity(), CITY);
        assertEquals(result.getAddress().getState(), STATE);
        assertEquals(result.getAddress().getPostalCode(), POSTAL_CODE);
        assertEquals(result.getAddress().getStreet(), STREET);
        assertEquals(result.getAddress().getNumber(), NUMBER);
    }

    @Test
    void mapDoctorListDtoFrom_withDoctor_shouldReturnDoctorListDto() {
        Doctor doctor = Doctor.builder().id(ID).name(NAME).crm(CRM).specialty(SPECIALTY).email(EMAIL).phone(PHONE)
                .build();

        DoctorListDto result = DoctorMapper.INSTANCE.mapDoctorListDtoFrom(doctor);

        assertEquals(result.getId(), ID);
        assertEquals(result.getName(), NAME);
        assertEquals(result.getCrm(), CRM);
        assertEquals(result.getSpecialty(), SPECIALTY);
    }

    @Test
    void mapDoctorReadDtoFrom_withDoctor_shouldReturnDoctorReadDto() {
        var address = Address.builder().city(CITY).state(STATE).postalCode(POSTAL_CODE).street(STREET).number(NUMBER)
                .build();

        Doctor doctor = Doctor.builder().id(ID).name(NAME).crm(CRM).specialty(SPECIALTY).email(EMAIL).phone(PHONE)
                .address(address).build();

        var result = DoctorMapper.INSTANCE.mapDoctorReadDtoFrom(doctor);

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
