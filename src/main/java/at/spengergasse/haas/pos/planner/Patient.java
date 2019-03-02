package at.spengergasse.haas.pos.planner;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Patient {
    private String firstname;
    private String sirname;
    private LocalDate birthday;
    private int age;
    private float height, weight;

    @Id
    @GeneratedValue
    private Long id;
    private Type type;


}
