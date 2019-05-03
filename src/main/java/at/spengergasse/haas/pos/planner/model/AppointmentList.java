package at.spengergasse.haas.pos.planner.model;


import at.spengergasse.haas.pos.planner.service.AppointmentListDto;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@Entity
public class AppointmentList extends AbstractModel{

    @OneToMany(mappedBy = "appointmentList",cascade = CascadeType.PERSIST)
    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentList(AppointmentListDto appointmentListDto){
        super(appointmentListDto);
        this.appointments = appointmentListDto.getAppointments()
                .stream().map(Appointment::new)
                .collect(Collectors.toList());
    }


}

