package at.spengergasse.haas.pos.planner.presentation;

import at.spengergasse.haas.pos.planner.service.AppointmentListDto;
import at.spengergasse.haas.pos.planner.service.UseCaseAppointmentListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AppointmentListController extends AbstractController<AppointmentListDto> {

    private final UseCaseAppointmentListService useCaseAppointmentListService;

    @GetMapping(path = "/appointmentList")
    public ResponseEntity<List<AppointmentListDto>> findAll() {
        return ResponseEntity.ok(useCaseAppointmentListService
                .findAllAppointmentLists()
                .stream()
                .map(this::addSelfLink)
                .collect(Collectors.toList()));
    }

    @PostMapping(path = "/appointmentList")
    public ResponseEntity<AppointmentListDto> create(@RequestBody AppointmentListDto appointmentList) {
        log.info("Create with: {}", appointmentList);
        return ResponseEntity.ok(useCaseAppointmentListService
                .saveAppointmentList(appointmentList)
                .orElseThrow(IllegalArgumentException::new));

    }

    @GetMapping(path = "/appointmentList/{identifier}")
    public ResponseEntity<AppointmentListDto> findById(@PathVariable String identifier) {

        return ResponseEntity.of(useCaseAppointmentListService
                .findAppointmentListById(identifier)
                .map(this::addSelfLink));
    }
}
