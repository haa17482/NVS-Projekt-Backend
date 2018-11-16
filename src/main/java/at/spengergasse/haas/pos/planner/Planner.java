package at.spengergasse.haas.pos.planner;

import java.time.LocalDate;

public class Planner
{
    public static void main(String [] args){
        Patient p1= new Patient();
        p1.createPatient("Sebastian", "Haas", LocalDate.of(2000,11,18), 17, Type.BOY);
        AppointmentList al= new AppointmentList();
        Appointment appointment= new Appointment();
        Appointment appointment2= new Appointment();


        appointment.makeAppointment("Test", 1, LocalDate.of(2018,12,10), p1);
        appointment2.makeAppointment("dhfjsdfhsgfusb", 0, LocalDate.of(2018,12,10),p1);

        al.add(appointment);
        al.add(appointment2);
        al.listAppointemnts();
    }

}
