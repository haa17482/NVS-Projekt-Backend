package at.spengergasse.haas.pos.planner;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class AppointmentList {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

   /* public List<Appointment> getAppointments() {
        return Collections.unmodifiableList(appointments);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void addAll(List<Appointment> appointments) {
        appointments.addAll(appointments);
    }

    public boolean containsAppointment(Appointment appointment) {
        return appointments.contains(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
*/
}

    /*public void add(Appointment appointment){
        appointments.add(appointment);

        if (appointments.contains(appointment)){
            System.out.println("Appointment was added!");
            System.out.println("--------------------------------------------------------");

        }
            else System.out.println("Appointment couldn't be added, check parameters!");
        }

    public void remove(Appointment appointment){
        if (appointments.contains(appointment)){
            appointments.remove(appointment);
            System.out.println("Appointment was deleted!");
            System.out.println("--------------------------------------------------------");
        }
        else System.out.println("Appointment couldn't be found!");
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
    public void listGirls(){
        System.out.println("All Girls:");
        for (int a=0;a<appointments.size();a++)
        {if (appointments.get(a).getPatient().getType()==Type.GIRL)
            System.out.println(appointments.get(a).getPatient().toString());
            if(a== appointments.size()-1){
                System.out.println("-------------------------------------------------------------------------------------------");
            }
        }

    }
    public void listBoys(){
        System.out.println("All Boys:");
        for (int a=0;a<appointments.size();a++)
        {if (appointments.get(a).getPatient().getType()==Type.BOY)
            System.out.println(appointments.get(a).getPatient().toString());
            if(a== appointments.size()-1){
                System.out.println("-------------------------------------------------------------------------------------------");
            }
        }
    }

    public void listMen(){
        System.out.println("All Men:");
        for (int a=0;a<appointments.size();a++)
        {if (appointments.get(a).getPatient().getType()==Type.MAN)
            System.out.println(appointments.get(a).getPatient().toString());
            if(a== appointments.size()-1){
                System.out.println("-------------------------------------------------------------------------------------------");
            }
        }
    }

    public void listWomen(){
        System.out.println("All Women:");
        int x=0;
        for (int a=0;a<appointments.size();a++)
        {
            if (appointments.get(a).getPatient().getType()==Type.WOMAN)
            System.out.println(appointments.get(a).getPatient().toString());
            else
                x++;
            if(a== appointments.size()-1){
                System.out.println("-------------------------------------------------------------------------------------------");
            }
        }
        if(x==0){
            System.out.println("No Appointments for Women!");
        }
    }

    public List getList(){
        return Collections.unmodifiableList(appointments);
    }*/

