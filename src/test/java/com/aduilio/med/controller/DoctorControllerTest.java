package com.aduilio.med.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aduilio.med.dto.DoctorCreateDto;
import com.aduilio.med.dto.DoctorListDto;
import com.aduilio.med.entity.Doctor;
import com.aduilio.med.exception.DoctorNotFoundException;
import com.aduilio.med.repository.DoctorRepository;

/**
 * Unit tests for {@link DoctorController}
 */
@ExtendWith(MockitoExtension.class)
public class DoctorControllerTest {

    private static final String ID = "id";

    @Mock
    private DoctorRepository mockDoctorRepository;

    @InjectMocks
    private DoctorController doctorController;

    @Test
    void create_withValue_shouldReturnId() {
        when(mockDoctorRepository.save(any(Doctor.class)))
            .thenReturn(Doctor.builder().id(ID).build());

        ResponseEntity<Doctor> result = doctorController.create(new DoctorCreateDto());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getId()).isEqualTo(ID);

        verify(mockDoctorRepository, times(1)).save(any(Doctor.class));
    }

    @Test
    void read_withValidId_shouldReturnDoctor() {
        when(mockDoctorRepository.findById(ID))
            .thenReturn(Optional.of(Doctor.builder().id(ID).build()));

        ResponseEntity<Doctor> result = doctorController.read(ID);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getId()).isEqualTo(ID);

        verify(mockDoctorRepository, times(1)).findById(ID);
    }

    @Test
    void read_withInvalidId_shouldThrowException() {
        when(mockDoctorRepository.findById(ID))
            .thenReturn(Optional.empty());

        DoctorNotFoundException response = assertThrows(DoctorNotFoundException.class, () -> doctorController.read(ID));

        assertThat(response.getMessage()).isEqualTo("Doctor " + ID + " not found.");

        verify(mockDoctorRepository, times(1)).findById(ID);
    }

    @Test
    void list_withValues_shouldReturnDoctorListDto() {
        Pageable pageable = PageRequest.of(0, 8);
        Page<Doctor> page = new PageImpl<>(Arrays.asList(Doctor.builder().id(ID).build()));

        when(mockDoctorRepository.findAll(pageable)).thenReturn(page);

        ResponseEntity<Page<DoctorListDto>> result = doctorController.list(pageable);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getContent()).hasSize(1);

        verify(mockDoctorRepository, times(1)).findAll(pageable);
    }
}
