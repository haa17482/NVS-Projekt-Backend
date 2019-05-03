package at.spengergasse.haas.pos.planner.model;

import at.spengergasse.haas.pos.planner.service.AppointmentDto;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@Entity
public class Appointment extends AbstractModel {

    private String title;
    private int priority;
    private LocalDate date;

    @OneToOne(cascade = {CascadeType.PERSIST})
    Patient patient;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private AppointmentList appointmentList;

    public Appointment(AppointmentDto appointmentDto){
        super(appointmentDto);
        this.title= appointmentDto.getTitle();
        this.priority= appointmentDto.getPriority();
        this.date = appointmentDto.getDate();
        this.patient= new Patient(appointmentDto.getPatient());
       // this.appointmentList= new AppointmentList(appointmentDto.getAppointmentList());
    }




}
