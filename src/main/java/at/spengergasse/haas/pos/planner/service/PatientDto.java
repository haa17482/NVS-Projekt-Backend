package at.spengergasse.haas.pos.planner.service;

import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PatientDto {

    private String identifier;
    private String firstname;
    private String sirname;
    private LocalDate birthday;
    private int age;
    private float height, weight;
    private Type type;

    public PatientDto(Patient patient) {
        this.identifier = patient.getIdentifier();
        this.firstname = patient.getFirstname();
        this.sirname = patient.getSirname();
        this.birthday = patient.getBirthday();
        this.age = patient.getAge();
        this.height = patient.getHeight();
        this.weight = patient.getWeight();
        this.type = patient.getType();
    }
}
