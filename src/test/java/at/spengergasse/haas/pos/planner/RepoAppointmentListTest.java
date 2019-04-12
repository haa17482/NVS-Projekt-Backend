package at.spengergasse.haas.pos.planner;

import at.spengergasse.haas.pos.planner.model.Appointment;
import at.spengergasse.haas.pos.planner.model.AppointmentList;
import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import at.spengergasse.haas.pos.planner.persistence.AppointmentListRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersistenceTestConfiguration.class)
@Transactional
public class RepoAppointmentListTest {

    @Autowired
    private AppointmentListRepository appointmentListRepository;
    private AppointmentList appointmentAppointmentList;
    private AppointmentList appointmentAppointmentList2;
    private List<Appointment> al;
    private List<Appointment> al2;
    private Appointment appointment;
    private Appointment appointment2;
    private List<AppointmentList> testList;
    private Patient patient;
    private Patient patient2;




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
        al.add(appointment);
        al.add(appointment2);

        al2= new ArrayList<>();
        al2.add(appointment);
        al2.add(appointment2);


        appointmentAppointmentList = AppointmentList.builder()
                .appointments(al)
                .build();

        appointmentAppointmentList2 = AppointmentList.builder()
                .appointments(al2)
                .build();

        testList= new ArrayList<>();

    }

    @Test
    void save() {
       appointmentListRepository.save(appointmentAppointmentList);
        assertNotNull(appointmentAppointmentList.getId());
    }

    @Test
    void findById() {
        appointmentListRepository.save(appointmentAppointmentList);
        assertNotNull(appointmentListRepository.findById(appointmentAppointmentList.getId()));
    }

    @Test
    @Transactional
    void findAll() {

        testList.add(appointmentAppointmentList);
        testList.add(appointmentAppointmentList2);

        appointmentListRepository.save(appointmentAppointmentList);
        appointmentListRepository.save(appointmentAppointmentList2);


        assertEquals(testList, appointmentListRepository.findAll());
    }

    @Test
    void delete(){
        appointmentListRepository.save(appointmentAppointmentList);
        assertNotNull(appointmentAppointmentList.getId());

        appointmentListRepository.delete(appointmentAppointmentList);
        assertTrue(appointmentListRepository.findById(appointmentAppointmentList.getId()).isEmpty());
    }

}
