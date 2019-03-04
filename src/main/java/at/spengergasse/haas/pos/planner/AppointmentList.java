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

    @OneToMany(mappedBy = "appointmentList",cascade = CascadeType.ALL)
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

