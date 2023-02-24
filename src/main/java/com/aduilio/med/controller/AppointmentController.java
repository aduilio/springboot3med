package com.aduilio.med.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aduilio.med.dto.AppointmentCreateDto;
import com.aduilio.med.dto.AppointmentReadDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    
    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentReadDto> create(@RequestBody @Valid AppointmentCreateDto appointmentDto){
        return ResponseEntity.ok(AppointmentReadDto.builder().build());
    }
}
