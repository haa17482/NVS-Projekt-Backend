package at.spengergasse.haas.pos.planner.presentation;

import at.spengergasse.haas.pos.planner.service.AppointmentListDto;
import at.spengergasse.haas.pos.planner.service.UseCaseAppointmentListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ ={@Autowired})
public class AppointmentListController {

    private final UseCaseAppointmentListService useCaseAppointmentListService;

    @GetMapping(path= "/appointmentList")
    public List<AppointmentListDto> findAll(){
        return useCaseAppointmentListService.findAllAppointmentLists();
    }

    @PostMapping(path = "/appointmentList")
    public AppointmentListDto create(@RequestBody AppointmentListDto appointmentList){
        log.info("Create with: {}",appointmentList);
        return useCaseAppointmentListService.saveAppointmentList(Optional.of(appointmentList))
                .orElseThrow(IllegalArgumentException::new);

    }
    @GetMapping(path = "/appointmentList/{identifier}")
    public AppointmentListDto findById(@PathVariable String identifier){

        return useCaseAppointmentListService.findAppointmentListById(identifier)
                .orElseThrow(IllegalArgumentException::new);
    }
}
