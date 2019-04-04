package at.spengergasse.haas.pos.planner.persistence;

import at.spengergasse.haas.pos.planner.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient,Long> {
}
