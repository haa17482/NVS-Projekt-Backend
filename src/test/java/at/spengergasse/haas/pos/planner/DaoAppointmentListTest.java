package at.spengergasse.haas.pos.planner;

import at.spengergasse.haas.pos.planner.model.Appointment;
import at.spengergasse.haas.pos.planner.model.AppointmentList;
import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import persistence.DaoAppointmentList;
import persistence.DaoPatient;
import persistence.JpaPrimerConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DaoAppointmentListTest {

    private static EntityManager entityManager;
    private static AppointmentList appointmentAppointmentList;
    private static AppointmentList appointmentAppointmentList2;
    private static DaoAppointmentList daoAppointmentList;
    private  List<Appointment> al;
    private  List<Appointment> a2;
    private static Appointment appointment;
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
        daoAppointmentList = applicationContext.getBean(DaoAppointmentList.class);
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
                .date(LocalDate.of(2019, 3, 3))
                .patient(patient)
                .build();

        appointment2 = Appointment.builder()
                .title("Musclecheck")
                .priority(1)
                .date(LocalDate.of(2019, 3, 5))
                .patient(patient2)
                .build();

        al= new ArrayList<>();
        a2= new ArrayList<>();
        al.add(appointment);
        al.add(appointment2);
        a2.add(appointment2);
        a2.add(appointment);

        appointmentAppointmentList = AppointmentList.builder()
                .appointments(al)
                .build();

        appointmentAppointmentList2 = AppointmentList.builder()
                .appointments(a2)
                .build();
    }

    @Test
    void findById() {
        var savedObject = daoAppointmentList.save(appointmentAppointmentList);
        var actualObject = daoAppointmentList.findById(savedObject.getId());
        assertEquals(savedObject, actualObject);
    }

    @Test
    void findAll() {
        List<AppointmentList> appointmentsLists = new ArrayList<>();
        daoAppointmentList.save(appointmentAppointmentList);
        appointmentsLists.add(appointmentAppointmentList);

        daoAppointmentList.save(appointmentAppointmentList2);
        appointmentsLists.add(appointmentAppointmentList2);

        assertEquals(appointmentsLists, daoAppointmentList.findAll());
    }

    @Test
    void save() {
        var result = daoAppointmentList.save(appointmentAppointmentList);
        assertNotNull(result.getId());
    }

    @Test
    void delete() {
        daoAppointmentList.save(appointmentAppointmentList);
        assertNotNull(appointmentAppointmentList.getId());

        daoAppointmentList.delete(appointmentAppointmentList);
        assertNull(appointmentAppointmentList.getId());
    }


}
