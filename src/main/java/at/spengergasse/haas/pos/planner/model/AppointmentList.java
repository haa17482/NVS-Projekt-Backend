package at.spengergasse.haas.pos.planner.model;


import at.spengergasse.haas.pos.planner.service.AppointmentListDto;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class AppointmentList extends AbstractPersistable<Long> {

    private String identifier;

    @OneToMany(mappedBy = "appointmentList",cascade = CascadeType.PERSIST)
    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentList(AppointmentListDto appointmentListDto){
        this.identifier= Optional.ofNullable(appointmentListDto.getIdentifier())
                .orElse(UUID.randomUUID().toString());
        this.appointments = appointmentListDto.getAppointments()
                .stream().map(Appointment::new)
                .collect(Collectors.toList());
    }


}

