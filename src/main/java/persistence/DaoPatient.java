package persistence;

import at.spengergasse.haas.pos.planner.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import java.util.List;

public class DaoPatient extends AbstractDao<Patient, Long> {

    public DaoPatient(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Patient> getPClass() {
        return Patient.class;
    }




    /*private EntityManager entityManager;

    public Patient findById(Long id) {
        return entityManager.find(Patient.class, id);
    }

    public List<Patient> findAll() {

        return entityManager.createQuery(
                "SELECT p from Patient p",
                Patient.class)
                .getResultList();
    }

    public Patient save(Patient patient) {
        entityManager.persist(patient);
        return patient;
    }


    public void delete(Patient patient) {
        entityManager.remove(patient);
        patient.setId(null);
    }

*/
}
