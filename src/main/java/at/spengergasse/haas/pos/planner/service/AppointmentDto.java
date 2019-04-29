package at.spengergasse.haas.pos.planner.service;

import at.spengergasse.haas.pos.planner.model.Appointment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AppointmentDto {

    private String identifier;
    private String title;
    private int priority;
    private LocalDate date;
    private PatientDto patient;

    public AppointmentDto(Appointment appointment){
        this.identifier = appointment.getIdentifier();
        this.title = appointment.getTitle();
        this.priority = appointment.getPriority();
        this.date = appointment.getDate();
        this.patient= new PatientDto(appointment.getPatient());
       // this.appointmentList= new AppointmentListDto(appointment.getAppointmentList());

    }
}
