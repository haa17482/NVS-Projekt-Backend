package at.spengergasse.haas.pos.test;

import at.spengergasse.haas.pos.planner.Appointment;
import at.spengergasse.haas.pos.planner.AppointmentList;
import at.spengergasse.haas.pos.planner.Patient;
import at.spengergasse.haas.pos.planner.Type;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {
    @Test
    public void test(){
        List<Appointment> test= new ArrayList<Appointment>();

        Patient p1= new Patient();
        p1.builder()
                .firstname("Leon")
                .sirname("Haas")
                .birthday(LocalDate.of(2002,10,14))
                .age(16)
                .height(1.79f)
                .weight(65f)
                .type(Type.BOY)
                .build();

        Patient p2= new Patient();
        p2.builder()
                .firstname("Sebastian")
                .sirname("Haas")
                .birthday(LocalDate.of(2000,11,18))
                .age(18)
                .height(1.80f)
                .weight(70f)
                .type(Type.MAN)
                .build();

        AppointmentList al= new AppointmentList();
        Appointment appointment= new Appointment();
        Appointment appointment2= new Appointment();

        appointment = Appointment.builder()
                .title("Bodycheck")
                .priority(2)
                .date(LocalDate.of(2019,3,3))
                .patient(p1)
                .build();

        appointment2 = Appointment.builder()
                .title("Musclecheck")
                .priority(1)
                .date(LocalDate.of(2019,3,5))
                .patient(p2)
                .build();

        al.addAppointment(appointment);
        al.addAppointment(appointment2);
        al.getAppointments();

        test.add(appointment);
        test.add(appointment2);

        assertEquals(al.getAppointments(),test);
        assertEquals(appointment.getPatient(),p1);
        assertEquals(appointment2.getPatient(),p2);
        assertEquals(p1.getType(), Type.MAN);
        assertEquals(p2.getType(), Type.BOY);






    }

}