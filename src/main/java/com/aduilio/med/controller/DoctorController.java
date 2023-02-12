package com.aduilio.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aduilio.med.dto.DoctorCreateDto;
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
		Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new DoctorNotFoundException(id));
        
		return ResponseEntity.ok(doctor);
	}
}
