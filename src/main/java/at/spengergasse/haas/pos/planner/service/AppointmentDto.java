package at.spengergasse.haas.pos.planner.service;

import at.spengergasse.haas.pos.planner.model.Appointment;
import at.spengergasse.haas.pos.planner.model.AppointmentList;
import at.spengergasse.haas.pos.planner.model.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

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
