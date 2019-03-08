package at.spengergasse.haas.pos.planner;

import lombok.*;
import persistence.BasePersistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
@Entity
public class Patient extends BasePersistable {
    private String firstname;
    private String sirname;
    private LocalDate birthday;
    private int age;
    private float height, weight;

    private Type type;


}
