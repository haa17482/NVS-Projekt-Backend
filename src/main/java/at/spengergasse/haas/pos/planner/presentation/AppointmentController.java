package at.spengergasse.haas.pos.planner.presentation;

import at.spengergasse.haas.pos.planner.service.AppointmentDto;
import at.spengergasse.haas.pos.planner.service.UseCaseAppointmentService;
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
public class AppointmentController extends AbstractController<AppointmentDto>{

    private final UseCaseAppointmentService useCaseAppointmentService;

    @GetMapping(path= "/appointments")
    public ResponseEntity<List<AppointmentDto>> findAll(){
        return ResponseEntity.ok(useCaseAppointmentService
                .findAllAppointments()
                .stream()
                .map(this::addSelfLink)
                .collect(Collectors.toList())
        );
    }

    @PostMapping(path = "/appointments")
    public ResponseEntity<AppointmentDto> create(@RequestBody AppointmentDto appointment){
        log.info("Create with: {}",appointment);
        return ResponseEntity.ok(useCaseAppointmentService
        .saveAppointment(appointment)
        .map(this::addSelfLink)
        .orElseThrow(IllegalArgumentException::new));

    }
    @GetMapping(path = "/appointments/{identifier}")
    public ResponseEntity<AppointmentDto> findById(@PathVariable String identifier){
        return ResponseEntity.of(useCaseAppointmentService
        .findAppointmentById(identifier)
        .map(this::addSelfLink));
    }


    @DeleteMapping(path = "/appointments/{identifier}")
    public ResponseEntity<Void> delete(@PathVariable String identifier) {
        log.info("Delete Appointment with id: {}", identifier);

        useCaseAppointmentService.deleteAppointmentByIdentifier(identifier);
        return ResponseEntity
                .ok()
                .build();
    }
}
