package at.spengergasse.haas.pos.planner;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

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

    @BeforeAll
    static void initialize() {
        entityManager = Persistence.createEntityManagerFactory("hif4b").
                createEntityManager();

        daoAppointment = new DaoAppointment(entityManager);

        entityManager.getTransaction().begin();
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
        List<Appointment> list= daoAppointment.findAll();

        assertEquals(appointments,list);
    }

    @Test
    void save(){
        var result= daoAppointment.save(appointment);
        assertNotNull(result.getId());

        appointment.setPriority(4);
        var secondResult= daoAppointment.save(appointment);
        assertEquals(result.getId(),secondResult.getId());
    }

    @Test
    void delete(){
        daoAppointment.save(appointment);
        assertNotNull(appointment.getId());

        daoAppointment.delete(appointment);
        assertNull(appointment.getId());
    }

    @AfterAll
    static void afterAll(){
        entityManager.getTransaction().commit();
    }
}
