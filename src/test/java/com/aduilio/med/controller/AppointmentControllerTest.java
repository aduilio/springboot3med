package com.aduilio.med.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.aduilio.med.dto.AppointmentCreateDto;
import com.aduilio.med.dto.AppointmentCreateDto.AppointmentDoctorDto;
import com.aduilio.med.dto.AppointmentCreateDto.AppointmentPatientDto;
import com.aduilio.med.entity.Appointment;
import com.aduilio.med.repository.AppointmentRepository;
import com.aduilio.med.validator.AppointmentDateValidator;
import com.aduilio.med.validator.AppointmentDoctorValidator;

/**
 * Unit test for the class {@link AppointmentController}.
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AppointmentControllerTest {

    private static final String APPOINTMENT_ID = "appointment_id";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AppointmentCreateDto> jacksonTesterCreate;

    @MockBean
    private AppointmentDoctorValidator appointmentDoctorValidatorMock;

    @MockBean
    private AppointmentDateValidator appointmentDateValidatorMock;

    @MockBean
    private AppointmentRepository appointmentRepositoryMock;

    @Mock
    private Page<Appointment> pageMock;

    @Test
    @WithMockUser
    void create_withInvalidPayload_shouldReturn400() throws Exception {
        var response = mockMvc.perform(post("/appointments")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    @WithMockUser
    void create_withValidPayload_shouldReturn200() throws Exception {
        when(appointmentRepositoryMock.save(any())).thenReturn(Appointment.builder().id(APPOINTMENT_ID).build());

        var doctor = AppointmentDoctorDto.builder().id(1L).build();
        var patient = AppointmentPatientDto.builder().name("name").phone("phone").build();
        var date = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10);

        var response = mockMvc.perform(post("/appointments").contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTesterCreate
                        .write(AppointmentCreateDto.builder().doctor(doctor).patient(patient).date(date).build())
                        .getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isNotNull();
        assertThat(response.getContentAsString()).contains(APPOINTMENT_ID);
    }

    @Test
    @WithMockUser
    void list_withInvalidPayload_shouldReturn400() throws Exception {
        when(appointmentRepositoryMock.findAll(any(Pageable.class))).thenReturn(pageMock);

        var response = mockMvc.perform(get("/appointments")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
         
        verify(pageMock).map(any());
    }
}
