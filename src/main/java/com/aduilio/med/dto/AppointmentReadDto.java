package com.aduilio.med.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an appoitment for reading information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentReadDto {

    private String id;
    private AppointmentDoctorDto doctor;
    private AppointmentPatientDto patient;
    private LocalDateTime date;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppointmentDoctorDto {

        private Long id;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppointmentPatientDto {

        private String name;
        private String phone;
    }
}
