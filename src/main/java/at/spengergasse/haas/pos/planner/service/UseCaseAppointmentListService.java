package at.spengergasse.haas.pos.planner.service;

import at.spengergasse.haas.pos.planner.model.AppointmentList;
import at.spengergasse.haas.pos.planner.persistence.AppointmentListRepository;
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
public class UseCaseAppointmentListService {

    private final AppointmentListRepository appointmentListRepository;

    public List<AppointmentListDto> findAllAppointmentLists(){
        return appointmentListRepository
                .findAll()
                .stream()
                .map(appointmentList -> new AppointmentListDto(appointmentList))
                .collect(Collectors.toList());
    }

    public Optional<AppointmentListDto> saveAppointmentList (AppointmentListDto appointmentListDto)
    {
        AppointmentList appointmentList = Optional.of(appointmentListDto).map(AppointmentList::new).get();
        return Optional.of(appointmentListRepository.save(appointmentList))
                .map(AppointmentListDto::new);
    }

    public Optional<AppointmentListDto> findAppointmentListById(String identifier){
        return appointmentListRepository.
                findByIdentifier(identifier)
                .map(AppointmentListDto::new);
    }
}
