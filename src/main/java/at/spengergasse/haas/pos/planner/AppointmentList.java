package at.spengergasse.haas.pos.planner;

import java.util.ArrayList;

public class AppointmentList {
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public void add(Appointment appointment){
        appointments.add(appointment);

        if (appointments.contains(appointment)){
            System.out.println("Appointment was added!");
            System.out.println(appointment.toString());
            System.out.println("-------------------------------------------------------------------------------------------");
        }
            else System.out.println("Appointment couldn't be added, check parameters!");
        }

        public void listAppointemnts(){
        System.out.println("All Appointments:");
        for (int a=0;a<appointments.size();a++)
        {
            System.out.println(appointments.get(a).toString());
            if(a== appointments.size()-1){
                System.out.println("-------------------------------------------------------------------------------------------");
            }
        }
        }
    }
