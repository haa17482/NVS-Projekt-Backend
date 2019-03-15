package at.spengergasse.haas.pos.planner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.DaoAppointment;
import persistence.DaoAppointmentList;
import persistence.DaoPatient;

@Component
@RequiredArgsConstructor(onConstructor_= {@Autowired})
public class JpaPrimerService {

    private final DaoPatient daoPatient;
    private final DaoAppointment daoAppointment;
    private final DaoAppointmentList daoAppointmentList;
}
