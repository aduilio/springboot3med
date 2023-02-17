package com.aduilio.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aduilio.med.dto.DoctorCreateDto;
import com.aduilio.med.dto.DoctorListDto;
import com.aduilio.med.dto.DoctorUpdateDto;
import com.aduilio.med.entity.Doctor;
import com.aduilio.med.exception.DoctorNotFoundException;
import com.aduilio.med.mapping.DoctorMapper;
import com.aduilio.med.repository.DoctorRepository;

import jakarta.validation.Valid;

/**
 * A controller to receive the requests for the path /doctors
 */
@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Doctor> create(@RequestBody @Valid DoctorCreateDto doctorDto) {
        Doctor doctor = DoctorMapper.INSTANCE.mapDoctorFrom(doctorDto);

        Doctor response = doctorRepository.save(doctor);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> read(@PathVariable String id) {
        return ResponseEntity.ok(findDoctor(id));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListDto>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<DoctorListDto> result = doctorRepository.findAll(pageable)
                .map(DoctorMapper.INSTANCE::mapDoctorListDtoFrom);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    @Transactional
    public void update(@PathVariable String id, @RequestBody @Valid DoctorUpdateDto doctorDto) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        DoctorMapper.INSTANCE.mapDoctorFrom(doctorDto, doctor);
    }

    private Doctor findDoctor(String id) {
        return doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));
    }
}
