package persistence;

import at.spengergasse.haas.pos.planner.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class DaoAppointment extends AbstractDao<Appointment,Long>{

    @Autowired
    public DaoAppointment(EntityManager entityManager){
        super(entityManager);
    }

    @Override
    protected Class<Appointment> getPClass() {
        return Appointment.class;
    }

}

