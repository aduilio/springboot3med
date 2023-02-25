package com.aduilio.med.dto;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an appointment for creating information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCreateDto {

    @Valid
    @NotNull(message = "{appointment.doctor.null}")
    private AppointmentDoctorDto doctor;

    @Valid
    @NotNull(message = "{appointment.patient.null}")
    private AppointmentPatientDto patient;

    @NotNull(message = "{appointment.date.blank}")
    @Future(message = "{appointment.date.past}")
    private LocalDateTime date;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppointmentDoctorDto {

        @NotNull(message = "{doctor.id.null}")
        private Long id;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppointmentPatientDto {

        @NotBlank(message = "{patient.name.blank}")
        private String name;

        @NotBlank(message = "{patient.phone.blank}")
        private String phone;
    }
}
