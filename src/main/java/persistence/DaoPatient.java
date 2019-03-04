package persistence;

import at.spengergasse.haas.pos.planner.Patient;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DaoPatient {

    private EntityManager entityManager;

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


}
