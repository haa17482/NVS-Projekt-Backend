package at.spengergasse.haas.pos.planner.service;

import at.spengergasse.haas.pos.planner.model.AppointmentList;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppointmentListDto extends AbstractDto{

    private List<AppointmentDto> appointments= new ArrayList<>();

    public AppointmentListDto(AppointmentList appointmentList){
        super(appointmentList.getIdentifier());
        this.appointments= appointmentList.getAppointments()
                .stream()
                .map(AppointmentDto::new)
                .collect(Collectors.toList());
    }

}
