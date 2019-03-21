package at.spengergasse.haas.pos.planner.model;

import lombok.*;
import at.spengergasse.haas.pos.planner.persistence.BasePersistable;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
@Entity
public class Appointment extends BasePersistable {

    private String title;
    private int priority;
    private LocalDate date;

    @OneToOne(cascade = {CascadeType.PERSIST})
    Patient patient;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private AppointmentList appointmentList;


}
