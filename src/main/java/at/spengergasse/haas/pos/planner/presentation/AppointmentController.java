package at.spengergasse.haas.pos.planner.presentation;

import at.spengergasse.haas.pos.planner.service.AppointmentDto;
import at.spengergasse.haas.pos.planner.service.UseCaseAppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AppointmentController {

    private final UseCaseAppointmentService useCaseAppointmentService;

    @GetMapping(path= "/appointments")
    public List<AppointmentDto> findAll(){
        return useCaseAppointmentService.findAllAppointments();
    }

    @PostMapping(path = "/appointments")
    public AppointmentDto create(@RequestBody AppointmentDto appointment){
        log.info("Create with: {}",appointment);
        return useCaseAppointmentService.saveAppointment(Optional.of(appointment))
                .orElseThrow(IllegalArgumentException::new);

    }
    @GetMapping(path = "/appointments/{identifier}")
    public AppointmentDto findById(@PathVariable String identifier){

        return useCaseAppointmentService.findAppointmentById(identifier)
                .orElseThrow(IllegalArgumentException::new);
    }
}
