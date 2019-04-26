package at.spengergasse.haas.presentation;


import at.spengergasse.haas.pos.planner.HifPosSpringBootApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HifPosSpringBootApplication.class)
@AutoConfigureMockMvc
public class AppointmentListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/appointmentList"))
                .andExpect(status().isOk());
    }

    @Test
    void createAppointmentList() throws Exception{
        mockMvc.perform(post("/appointmentList")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"appointments\": [\n" +
                        "        {\n" +
                        "            \"title\": \"Musclecheck\",\n" +
                        "            \"priority\": 1,\n" +
                        "            \"patient\": {\n" +
                        "                \"firstname\": \"Basti\",\n" +
                        "                \"sirname\": \"Haas\"\n" +
                        "            }\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identifier").exists());
    }

   /* @Test
    void findById() throws Exception {
        mockMvc.perform(get("/appointmentList/{identifier}"))
                .andExpect(status().isOk());
    }*/

}
