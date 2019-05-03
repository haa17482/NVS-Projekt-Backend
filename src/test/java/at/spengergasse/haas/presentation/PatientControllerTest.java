package at.spengergasse.haas.presentation;

import at.spengergasse.haas.pos.planner.HifPosSpringBootApplication;
import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import at.spengergasse.haas.pos.planner.service.PatientDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HifPosSpringBootApplication.class)
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private PatientDto patient;

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
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());
    }

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identifier")
                .exists());
    }

   @Test
    void findById() throws Exception {
       MvcResult result = mockMvc.perform(
               post("/patients")
                       .contentType(MediaType.APPLICATION_JSON_UTF8)
                       .content(objectMapper.writeValueAsString(patient))).andReturn();

       PatientDto patient = objectMapper.readValue(result.getResponse().getContentAsString(), PatientDto.class);

       mockMvc.perform(get("/patients/" + patient.getIdentifier())
                       .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
               .andExpect(jsonPath("$.identifier")
               .value(patient.getIdentifier()));
   }

}
