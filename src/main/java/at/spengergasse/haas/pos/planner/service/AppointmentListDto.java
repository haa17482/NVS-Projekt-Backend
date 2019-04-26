package at.spengergasse.haas.pos.planner.service;

import at.spengergasse.haas.pos.planner.model.Appointment;
import at.spengergasse.haas.pos.planner.model.AppointmentList;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AppointmentListDto {

    private String identifier;
    private List<AppointmentDto> appointments= new ArrayList<>();

    public AppointmentListDto(AppointmentList appointmentList){
        this.identifier=appointmentList.getIdentifier();
        this.appointments= appointmentList.getAppointments()
                .stream()
                .map(AppointmentDto::new)
                .collect(Collectors.toList());
    }

}
