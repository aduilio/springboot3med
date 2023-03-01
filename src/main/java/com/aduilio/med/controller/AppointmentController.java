package com.aduilio.med.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aduilio.med.dto.AppointmentCreateDto;
import com.aduilio.med.dto.AppointmentReadDto;
import com.aduilio.med.mapping.AppointmentMapper;
import com.aduilio.med.repository.AppointmentRepository;
import com.aduilio.med.validator.AppointmentValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private List<AppointmentValidator> validators;

    private AppointmentMapper appointmentMapper = AppointmentMapper.INSTANCE;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentReadDto> create(@RequestBody @Valid AppointmentCreateDto appointmentDto) {
        validators.forEach(validator -> validator.validate(appointmentDto));

        var appointment = appointmentMapper.mapAppointmentFrom(appointmentDto);
        var result = appointmentRepository.save(appointment);
        var response = appointmentMapper.mapAppointmentReadDtoFrom(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentReadDto>> list(
            @PageableDefault(size = 10, sort = {"date"}) final Pageable pageable) {
        final var result = appointmentRepository.findAll(pageable).map(appointmentMapper::mapAppointmentReadDtoFrom);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String id) {
        appointmentRepository.deleteById(id);
    }
}
