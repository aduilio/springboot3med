package com.aduilio.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aduilio.med.dto.DoctorCreateDto;
import com.aduilio.med.dto.DoctorListDto;
import com.aduilio.med.dto.DoctorReadDto;
import com.aduilio.med.dto.DoctorUpdateDto;
import com.aduilio.med.entity.Doctor;
import com.aduilio.med.exception.DoctorNotFoundException;
import com.aduilio.med.mapping.DoctorMapper;
import com.aduilio.med.repository.DoctorRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

/**
 * A controller to receive the requests for the path /doctors
 */
@RestController
@RequestMapping("doctors")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    private DoctorMapper doctorMapper = DoctorMapper.INSTANCE;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorReadDto> create(@RequestBody @Valid final DoctorCreateDto doctorDto,
            UriComponentsBuilder uriBuilder) {
        final var doctor = doctorMapper.mapDoctorFrom(doctorDto);
        final var result = doctorRepository.save(doctor);
        final var response = doctorMapper.mapDoctorReadDtoFrom(result);

        final var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorReadDto> read(@PathVariable final String id) {
        return createReadResponse(findDoctor(id));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListDto>> list(
            @PageableDefault(size = 10, sort = {"name"}) final Pageable pageable) {
        final var result = doctorRepository.findAllByActiveTrue(pageable).map(doctorMapper::mapDoctorListDtoFrom);

        return ResponseEntity.ok(result);
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<DoctorReadDto> update(@PathVariable final String id,
            @RequestBody @Valid final DoctorUpdateDto doctorUpdateDto) {
        final var doctor = getDoctor(id);
        doctorMapper.mapDoctorFrom(doctorUpdateDto, doctor);
        return createReadResponse(doctor);
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String id) {
        final var doctor = getDoctor(id);
        doctor.setActive(false);
    }

    private Doctor findDoctor(final String id) {
        return doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));
    }

    private Doctor getDoctor(final String id) {
        try {
            return doctorRepository.getReferenceById(id);
        } catch (EntityNotFoundException ex) {
            throw new DoctorNotFoundException(id);
        }
    }

    private ResponseEntity<DoctorReadDto> createReadResponse(final Doctor doctor) {
        final var doctorReadDto = doctorMapper.mapDoctorReadDtoFrom(doctor);
        return ResponseEntity.ok(doctorReadDto);
    }
}
