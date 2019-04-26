package at.spengergasse.haas.pos.planner.model;

import at.spengergasse.haas.pos.planner.service.PatientDto;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@Entity
public class Patient extends AbstractPersistable<Long> {

    private String identifier;
    private String firstname;
    private String sirname;
    private LocalDate birthday;
    private int age;
    private float height, weight;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Patient(PatientDto patientDto) {
        this.identifier =  Optional.ofNullable(patientDto.getIdentifier())
                .orElse(UUID.randomUUID().toString());
        this.firstname = patientDto.getFirstname();
        this.sirname = patientDto.getSirname();
        this.birthday = patientDto.getBirthday();
        this.age = patientDto.getAge();
        this.height = patientDto.getHeight();
        this.weight = patientDto.getWeight();
    }
}
