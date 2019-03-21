package at.spengergasse.haas.pos.planner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import at.spengergasse.haas.pos.planner.persistence.DaoAppointment;
import at.spengergasse.haas.pos.planner.persistence.DaoAppointmentList;
import at.spengergasse.haas.pos.planner.persistence.DaoPatient;

@Component
@RequiredArgsConstructor(onConstructor_= {@Autowired})
public class JpaPrimerService {

    private final DaoPatient daoPatient;
    private final DaoAppointment daoAppointment;
    private final DaoAppointmentList daoAppointmentList;
}
