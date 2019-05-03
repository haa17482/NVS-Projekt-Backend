package at.spengergasse.haas.pos.planner.service;

import at.spengergasse.haas.pos.planner.model.Appointment;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppointmentDto extends AbstractDto{

    private String title;
    private int priority;
    private LocalDate date;
    private PatientDto patient;

    public AppointmentDto(Appointment appointment){
        super(appointment.getIdentifier());
        this.title = appointment.getTitle();
        this.priority = appointment.getPriority();
        this.date = appointment.getDate();
        this.patient= new PatientDto(appointment.getPatient());
       // this.appointmentList= new AppointmentListDto(appointment.getAppointmentList());

    }
}
