package at.spengergasse.haas.pos.planner.model;

import lombok.*;
import at.spengergasse.haas.pos.planner.persistence.BasePersistable;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
//@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
@Entity
public class Patient extends BasePersistable {
    private String firstname;
    private String sirname;
    private LocalDate birthday;
    private int age;
    private float height, weight;

    @Enumerated(EnumType.STRING)
    private Type type;


}
