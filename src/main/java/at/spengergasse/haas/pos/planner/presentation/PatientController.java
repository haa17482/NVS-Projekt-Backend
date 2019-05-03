package at.spengergasse.haas.pos.planner.presentation;

import at.spengergasse.haas.pos.planner.service.PatientDto;
import at.spengergasse.haas.pos.planner.service.UseCasePatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PatientController extends AbstractController<PatientDto>{

    private final UseCasePatientService useCasePatientService;

    @GetMapping(path= "/patients")
    public ResponseEntity<List<PatientDto>> findAll(){

        return ResponseEntity.ok(useCasePatientService
                .findAllPersons()
                .stream()
                .map(this::addSelfLink)
                .collect(Collectors.toList())
        );
    }

    @PostMapping(path = "/patients")
    public ResponseEntity<PatientDto> create(@RequestBody PatientDto patient){
        log.info("Create with: {}",patient);
        return ResponseEntity.ok(useCasePatientService
        .savePerson(patient)
        .map(this::addSelfLink)
        .orElseThrow(IllegalArgumentException::new));

    }
    @GetMapping(path = "/patients/{identifier}")
    public ResponseEntity<PatientDto> findById(@PathVariable String identifier){

        return ResponseEntity.of(useCasePatientService
        .findPersonById(identifier)
        .map(this::addSelfLink));
    }

}
