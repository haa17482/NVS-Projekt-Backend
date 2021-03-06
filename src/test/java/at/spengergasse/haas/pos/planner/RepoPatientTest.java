package at.spengergasse.haas.pos.planner;

import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import at.spengergasse.haas.pos.planner.persistence.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@Transactional
class RepoPatientTest {

    @Autowired
    private PatientRepository patientRepository;

    private Patient patient;
    private Patient patient2;
    private List<Patient> patients;

    @BeforeEach
    void beforeEach() {
        patients = new ArrayList<>();


        patient = Patient.builder()
                //.identifier("1")
                .firstname("Sebastian")
                .sirname("Haas")
                .birthday(LocalDate.of(2000, 11, 18))
                .age(18)
                .height(1.80f)
                .weight(70f)
                .type(Type.MAN)
                .build();

        patient2 = Patient.builder()
               // .identifier("2")
                .firstname("Tobias")
                .sirname("Furtlehner")
                .birthday(LocalDate.of(2002, 6, 10))
                .age(16)
                .height(1.82f)
                .weight(80f)
                .type(Type.BOY)
                .build();
    }


    @Test
    void save() {
        patientRepository.save(patient);
        assertNotNull(patient.getId());
    }

    @Test
    void findById() {
        patientRepository.save(patient);
        assertNotNull(patientRepository.findById(patient.getId()));
    }

    @Test
    void findAll() {
        patients.add(patient);
        patients.add(patient2);


        patientRepository.save(patient);
        patientRepository.save(patient2);


        assertEquals(patients, patientRepository.findAll());
    }

    @Test
    void delete(){
        patientRepository.save(patient);
        assertNotNull(patient.getId());

        patientRepository.delete(patient);
        assertTrue(patientRepository.findById(patient.getId()).isEmpty());
    }


}