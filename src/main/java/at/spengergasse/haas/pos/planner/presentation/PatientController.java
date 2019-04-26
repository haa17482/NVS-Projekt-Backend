package at.spengergasse.haas.pos.planner.presentation;

import at.spengergasse.haas.pos.planner.service.PatientDto;
import at.spengergasse.haas.pos.planner.service.UseCasePatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PatientController {

    private final UseCasePatientService useCasePatientService;

    @GetMapping(path= "/patients")
    public List<PatientDto> findAll(){
        return useCasePatientService.findAllPersons();
    }

    @PostMapping(path = "/patients")
    public PatientDto create(@RequestBody PatientDto patient){
        log.info("Create with: {}",patient);
        return useCasePatientService.savePerson(Optional.of(patient))
                .orElseThrow(IllegalArgumentException::new);

    }
    @GetMapping(path = "/patients/{identifier}")
    public PatientDto findById(@PathVariable String identifier){

        return useCasePatientService.findPersonById(identifier)
                .orElseThrow(IllegalArgumentException::new);
    }
}
