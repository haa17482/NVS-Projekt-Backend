package at.spengergasse.haas.pos.planner.model;


import lombok.*;
import at.spengergasse.haas.pos.planner.persistence.BasePersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
@Entity
public class AppointmentList extends BasePersistable {


    @OneToMany(mappedBy = "appointmentList",cascade = CascadeType.PERSIST)
    private List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> getAppointments(){
        return Collections.unmodifiableList(appointments);
    }

    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
        if (appointment.getAppointmentList() != this){
            appointment.setAppointmentList(this);
        }
    }

    public void addAllAppointments(List<Appointment> appointments){
        for (Appointment appointment : appointments) {
            this.appointments.add(appointment);
            if(appointment.getAppointmentList() != this){
                appointment.setAppointmentList(this);
            }
        }
    }

    public boolean containsAppointment(Appointment appointment){
        return appointments.contains(appointment);
    }

    public void removeAppointment(Appointment appointment){
        appointments.remove(appointment);
        appointment.setAppointmentList(null);
    }

}

