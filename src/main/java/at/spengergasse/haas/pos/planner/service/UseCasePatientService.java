package at.spengergasse.haas.pos.planner.service;

import at.spengergasse.haas.pos.planner.model.Patient;
import at.spengergasse.haas.pos.planner.persistence.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UseCasePatientService {

    private final PatientRepository patientRepository;

    public List<PatientDto> findAllPersons(){
        return patientRepository
                .findAll()
                .stream()
                /*.filter(person->person.getFirstname().equals("Sebastian"))
                .map(person-> {person.setFirstname(person.getFirstname().toUpperCase());
                return person;
                })*/
                .map(patient -> new PatientDto(patient))
                .collect(Collectors.toList());
    }

    public Optional<PatientDto> savePerson(PatientDto patientDto) {
        Patient patient = Optional.of(patientDto).map(Patient::new).get();
        return Optional.of(patientRepository.save(patient))
                .map(PatientDto::new);
    }

    public Optional<PatientDto> findPersonById(String identifier){
        return patientRepository.
                findByIdentifier(identifier)
                .map(PatientDto::new);
    }

    public void deletePatientByIdentifier(String identifier) {
        patientRepository.deleteByIdentifier(identifier);
    }
}
