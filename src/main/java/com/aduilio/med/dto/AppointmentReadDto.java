package com.aduilio.med.dto;

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
    private AppoitmentPatientDto patient;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class AppointmentDoctorDto {

        private Long id;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppoitmentPatientDto {

        private String name;
        private String phone;
    }
}
