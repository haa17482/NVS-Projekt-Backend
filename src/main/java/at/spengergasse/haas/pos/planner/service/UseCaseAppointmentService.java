package at.spengergasse.haas.pos.planner.service;


import at.spengergasse.haas.pos.planner.model.Appointment;
import at.spengergasse.haas.pos.planner.persistence.AppointmentRepository;
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
public class UseCaseAppointmentService {

    private final AppointmentRepository appointmentRepository;

    public List<AppointmentDto> findAllAppointments(){
        return appointmentRepository
                .findAll()
                .stream()
                /*.filter(person->person.getFirstname().equals("Sebastian"))
                .map(person-> {person.setFirstname(person.getFirstname().toUpperCase());
                return person;
                })*/
                .map(appointment -> new AppointmentDto(appointment))
                .collect(Collectors.toList());
    }

    public Optional<AppointmentDto> saveAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = Optional.of(appointmentDto).map(Appointment::new).get();
        return Optional.of(appointmentRepository.save(appointment))
                .map(AppointmentDto::new);
    }

    public Optional<AppointmentDto> findAppointmentById(String identifier){
        return appointmentRepository.
                findByIdentifier(identifier)
                .map(AppointmentDto::new);
    }
}
