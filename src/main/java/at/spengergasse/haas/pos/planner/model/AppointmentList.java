package at.spengergasse.haas.pos.planner.model;


import lombok.*;
import at.spengergasse.haas.pos.planner.persistence.BasePersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
//@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
@Entity
public class AppointmentList extends BasePersistable {


    @OneToMany(mappedBy = "appointmentList",cascade = CascadeType.PERSIST)
    private List<Appointment> appointments = new ArrayList<>();


}

