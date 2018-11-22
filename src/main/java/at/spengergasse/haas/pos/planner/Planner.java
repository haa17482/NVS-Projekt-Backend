package at.spengergasse.haas.pos.planner;

import java.time.LocalDate;

public class Planner
{
    public static void main(String [] args){
        Patient p1= new Patient();
        p1.createPatient("Sebastian", "Haas", LocalDate.of(2000,11,18), 18, Type.MAN);

        Patient p2= new Patient();
        p2.createPatient("Anika", "Taroncha", LocalDate.of(2001,10,31), 17, Type.GIRL);

        AppointmentList al= new AppointmentList();
        Appointment appointment= new Appointment();
        Appointment appointment2= new Appointment();


        appointment.makeAppointment("MuscleCheck", 1, LocalDate.of(2018,12,18), p1);
        appointment2.makeAppointment("BodyCheck", 0, LocalDate.of(2018,11,30),p2);

        al.add(appointment);
        al.add(appointment2);
        al.listAppointemnts();
    }

}
