package at.spengergasse.haas.pos.planner;

import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import persistence.DaoPatient;
import persistence.JpaPrimerConfiguration;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DaoPatientTest {

    private static EntityManager entityManager;
    private static DaoPatient daoPatient;
    private static Patient patient;
    private static Patient patient2;


    @BeforeEach
    void initializeDao() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(
                        JpaPrimerConfiguration.class
                );
        entityManager = applicationContext.getBean(EntityManager.class);
        daoPatient = applicationContext.getBean(DaoPatient.class);
    }

    @BeforeEach
    void startTransaction() {
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void endTransaction() {
        entityManager.getTransaction().commit();
    }



    @BeforeEach
    void beforeEach() {
        patient = Patient.builder()
                .firstname("Sebastian")
                .sirname("Haas")
                .birthday(LocalDate.of(2000, 11, 18))
                .age(18)
                .height(1.80f)
                .weight(70f)
                .type(Type.MAN)
                .build();

        patient2 = Patient.builder()
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
    void findById() {
        var savedObject = daoPatient.save(patient);
        assertEquals(patient, daoPatient.findById(savedObject.getId()));
    }

    @Test
    void save() {
        var result = daoPatient.save(patient);
        assertNotNull(result.getId());
    }

    @Test
    void findAll() {
        List<Patient> patients = new ArrayList<>();
        daoPatient.save(patient);
        patients.add(patient);

        daoPatient.save(patient2);
        patients.add(patient2);

        assertEquals(patients, daoPatient.findAll());
    }

    @Test
    void delete() {
        daoPatient.save(patient);
        assertNotNull(patient.getId());

        daoPatient.delete(patient);
        assertNull(patient.getId());
    }


}