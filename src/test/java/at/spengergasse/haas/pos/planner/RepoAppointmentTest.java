package at.spengergasse.haas.pos.planner;

import at.spengergasse.haas.pos.planner.model.Appointment;
import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import at.spengergasse.haas.pos.planner.persistence.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class RepoAppointmentTest {


    @Autowired
    private AppointmentRepository appointmentRepository;
    private Appointment appointment;
    private Appointment appointment2;
    private Patient patient;
    private Patient patient2;


    @BeforeEach
    void beforeEach() {

        patient = Patient.builder()
               // .identifier("1")
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


        appointment = Appointment.builder()
                //.identifier("1")
                .title("Bodycheck")
                .priority(2)
                .date(LocalDate.of(2019,3,3))
                .patient(patient)
                .build();

        appointment2 = Appointment.builder()
               // .identifier("2")
                .title("Musclecheck")
                .priority(1)
                .date(LocalDate.of(2019,3,5))
                .patient(patient2)
                .build();

    }


    @Test

    void save(){
        appointmentRepository.save(appointment);
        assertNotNull(appointment.getId());
    }

    @Test
    void findById(){
        appointmentRepository.save(appointment);
        assertNotNull(appointmentRepository.findById(appointment.getId()));
    }

    @Test
    @Transactional
    void findAll(){
        List<Appointment> appointments = new ArrayList<>();
        appointmentRepository.save(appointment);
        appointments.add(appointment);

        appointmentRepository.save(appointment2);
        appointments.add(appointment2);

        assertEquals(appointments,appointmentRepository.findAll());
    }

    @Test
    void delete(){
        appointmentRepository.save(appointment);
        assertNotNull(appointment.getId());

        appointmentRepository.delete(appointment);
        assertTrue(appointmentRepository.findById(appointment.getId()).isEmpty());
    }



}
