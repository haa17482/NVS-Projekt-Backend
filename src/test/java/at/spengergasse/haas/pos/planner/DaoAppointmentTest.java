package at.spengergasse.haas.pos.planner;

import at.spengergasse.haas.pos.planner.model.Appointment;
import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import persistence.DaoAppointment;
import persistence.DaoPatient;
import persistence.JpaPrimerConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DaoAppointmentTest {

    private static EntityManager entityManager;
    private static Appointment appointment;
    private static DaoAppointment daoAppointment;
    private static Appointment appointment2;
    private static Patient patient;
    private static Patient patient2;

    @BeforeEach
    void initializeDao() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(
                        JpaPrimerConfiguration.class
                );
        entityManager = applicationContext.getBean(EntityManager.class);
        daoAppointment = applicationContext.getBean(DaoAppointment.class);
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


        appointment = Appointment.builder()
                .title("Bodycheck")
                .priority(2)
                .date(LocalDate.of(2019,3,3))
                .patient(patient)
                .build();

        appointment2 = Appointment.builder()
                .title("Musclecheck")
                .priority(1)
                .date(LocalDate.of(2019,3,5))
                .patient(patient2)
                .build();
    }

    @Test
    void findById(){
        var savedObject = daoAppointment.save(appointment);
        var actualObject = daoAppointment.findById(savedObject.getId());
        assertEquals(savedObject,actualObject);
    }

    @Test
    void findAll(){
        List<Appointment> appointments = new ArrayList<>();
        daoAppointment.save(appointment);
        appointments.add(appointment);

        daoAppointment.save(appointment2);
        appointments.add(appointment2);

        assertEquals(appointments,daoAppointment.findAll());
    }

    @Test
    void save(){
        var result= daoAppointment.save(appointment);
        assertNotNull(result.getId());
    }

    @Test
    void delete(){
        daoAppointment.save(appointment);
        assertNotNull(appointment.getId());

        daoAppointment.delete(appointment);
        assertNull(appointment.getId());
    }


}
