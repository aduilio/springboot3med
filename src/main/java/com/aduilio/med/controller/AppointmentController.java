package com.aduilio.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aduilio.med.dto.AppointmentCreateDto;
import com.aduilio.med.dto.AppointmentReadDto;
import com.aduilio.med.mapping.AppointmentMapper;
import com.aduilio.med.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    private AppointmentMapper appointmentMapper = AppointmentMapper.INSTANCE;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentReadDto> create(@RequestBody @Valid AppointmentCreateDto appointmentDto) {
        var appointment = appointmentService.schedule(appointmentDto);
        var response = appointmentMapper.mapAppointmentReadDtoFrom(appointment);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentReadDto>> list(
            @PageableDefault(size = 10, sort = {"date"}) final Pageable pageable) {
        final var result = appointmentService.list(pageable).map(appointmentMapper::mapAppointmentReadDtoFrom);

        return ResponseEntity.ok(result);
    }
}
