package at.spengergasse.haas.presentation;


import at.spengergasse.haas.pos.planner.HifPosSpringBootApplication;
import at.spengergasse.haas.pos.planner.model.Appointment;
import at.spengergasse.haas.pos.planner.model.AppointmentList;
import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import at.spengergasse.haas.pos.planner.service.AppointmentDto;
import at.spengergasse.haas.pos.planner.service.AppointmentListDto;
import at.spengergasse.haas.pos.planner.service.PatientDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HifPosSpringBootApplication.class)
@AutoConfigureMockMvc
public class AppointmentListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private PatientDto patient;
    private Patient testpatient;
    private AppointmentDto appointment;
    private Appointment testappoinment;
    private AppointmentListDto appointmentList;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        this.patient= new PatientDto(Patient.builder()
                .firstname("Sebastian")
                .sirname("Haas")
                .birthday(LocalDate.of(2000,11,18))
                .age(18)
                .height(1.8f)
                .weight(70)
                .type(Type.MAN)
                .build());
        this.testpatient= new Patient(patient);


        this.appointment = new AppointmentDto(Appointment.builder()
                .title("Musclecheck")
                .date(LocalDate.of(2019,5,3))
                .priority(1)
                .patient(testpatient)
                .appointmentList(null)
                .build());
        this.testappoinment= new Appointment(appointment);

        List<Appointment> testlist= new ArrayList<Appointment>();
        testlist.add(testappoinment);

        this.appointmentList = new AppointmentListDto(AppointmentList.builder()
                .appointments(testlist)
                .build());
    }


    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/appointmentList"))
                .andExpect(status().isOk());
    }

    @Test
    void createAppointment() throws Exception{
        mockMvc.perform(post("/appointmentList")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(appointmentList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identifier")
                        .exists());
    }


    @Test
    void findById() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/appointmentList")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(appointmentList))).andReturn();

        AppointmentListDto appointmentList = objectMapper.readValue(result.getResponse().getContentAsString(), AppointmentListDto.class);

        mockMvc.perform(get("/appointmentList/" + appointmentList.getIdentifier())
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$.identifier")
                        .value(appointmentList.getIdentifier()));
    }

    @Test
    void deleteTest() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/appointmentList")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(appointmentList)))
                .andReturn();

        AppointmentListDto appointmentListDto = objectMapper.readValue(result.getResponse().getContentAsString(), AppointmentListDto.class);

        mockMvc.perform(delete("/appointmentList/" + appointmentListDto.getIdentifier())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        mockMvc.perform(get("/appointmentList/" + appointmentListDto.getIdentifier())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }
}
