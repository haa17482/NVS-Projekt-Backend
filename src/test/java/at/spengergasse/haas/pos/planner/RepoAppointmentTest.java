package at.spengergasse.haas.pos.planner;

import at.spengergasse.haas.pos.planner.model.Appointment;
import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import at.spengergasse.haas.pos.planner.persistence.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RepoAppointmentTest {

    private static EntityManager entityManager;
    private static Appointment appointment;
    private static AppointmentRepository daoAppointment;
    private static Appointment appointment2;
    private static Patient patient;
    private static Patient patient2;


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
    void save(){
        daoAppointment.save(appointment);
        assertNotNull(appointment.getId());
    }



}
