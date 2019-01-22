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
        p1.createPatient("Sebastian", "Haas", LocalDate.of(2000,11,18), 18, 1.80f,71,Type.MAN);

        Patient p2= new Patient();
        p2.createPatient("Gabriel", "Zeisz", LocalDate.of(2001,10,31), 17,1.90f,80, Type.BOY);

        AppointmentList al= new AppointmentList();
        Appointment appointment= new Appointment();
        Appointment appointment2= new Appointment();

        appointment.makeAppointment((long) 1,"MuscleCheck", 1, LocalDate.of(2018,12,18), p1);
        appointment2.makeAppointment((long) 2,"BodyCheck", 0, LocalDate.of(2018,11,30),p2);

        al.add(appointment);
        al.add(appointment2);
        al.listAppointemnts();
        al.listBoys();
        al.listMen();
        al.listWomen();
        al.listAppointemnts();

        test.add(appointment);
        test.add(appointment2);

        assertEquals(al.getList(),test);
        assertEquals(appointment.getPatient(),p1);
        assertEquals(appointment2.getPatient(),p2);
        assertEquals(p1.getType(), Type.MAN);
        assertEquals(p2.getType(), Type.BOY);






    }

}