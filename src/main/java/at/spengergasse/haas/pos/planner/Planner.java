package at.spengergasse.haas.pos.planner;

import java.time.LocalDate;

public class Planner
{



    public static void main(String [] args){
    System.out.println("Hello there....General Kenobi!");
    System.out.println("You probably didnt recognize me, because of my red arm!");
    System.out.println("Jason Bourne! It´s Jesus Christ!");

    Appointment a= new Appointment();
    a.makeAppointment("Test", 1, new LocalDate.of(2018,5,10));
    }
}
