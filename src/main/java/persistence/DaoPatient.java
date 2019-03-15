package persistence;

import at.spengergasse.haas.pos.planner.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class DaoPatient extends AbstractDao<Patient, Long> {

    @Autowired
    public DaoPatient(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Patient> getPClass() {
        return Patient.class;
    }

}
