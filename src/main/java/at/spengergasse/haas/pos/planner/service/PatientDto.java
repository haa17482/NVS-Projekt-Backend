package at.spengergasse.haas.pos.planner.service;

import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.model.Type;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PatientDto extends AbstractDto{

    private String firstname;
    private String sirname;
    private LocalDate birthday;
    private int age;
    private float height, weight;
    private Type type;

    public PatientDto(Patient patient) {
        super(patient.getIdentifier());
        this.firstname = patient.getFirstname();
        this.sirname = patient.getSirname();
        this.birthday = patient.getBirthday();
        this.age = patient.getAge();
        this.height = patient.getHeight();
        this.weight = patient.getWeight();
        this.type = patient.getType();
    }
}
