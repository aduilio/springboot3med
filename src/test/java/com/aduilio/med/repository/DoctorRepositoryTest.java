package com.aduilio.med.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.aduilio.med.entity.Address;
import com.aduilio.med.entity.Doctor;
import com.aduilio.med.entity.Specialty;

/**
 * Unit tests for the class {@link DoctorRepository}.
 */
@DataJpaTest
public class DoctorRepositoryTest {

    @Mock
    private Pageable pageableMock;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findAllByActiveTrue_withActiveDoctor_shouldReturnFalse() {
        when(pageableMock.getSort()).thenReturn(Sort.unsorted());
        when(pageableMock.getPageSize()).thenReturn(20);
        createDoctor(true);
        var result = doctorRepository.findAllByActiveTrue(pageableMock);

        assertThat(result.getContent()).hasSize(1);
    }

    @Test
    void isActive_withActiveDoctor_shouldReturnTrue() {
        var doctorId = createDoctor(true);

        assertThat(doctorRepository.isActive(doctorId)).isTrue();
    }

    @Test
    void isActive_withInactiveDoctor_shouldReturnFalse() {
        var doctorId = createDoctor(false);

        assertThat(doctorRepository.isActive(doctorId)).isFalse();
    }

    private String createDoctor(boolean active) {
        var address = Address.builder().city("city").state("state").postalCode("12345").street("street").number("123")
                .build();
        return entityManager.persist(Doctor.builder().name("name").crm("12345").email("email@email.com").phone("123456")
                .specialty(Specialty.CARDIOLOGY).active(active).address(address).build()).getId();
    }
}
