package at.spengergasse.haas.pos.planner;

import lombok.*;
import persistence.BasePersistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    @OneToOne(cascade = {CascadeType.ALL})
    Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    private AppointmentList appointmentList;

}
