package at.spengergasse.haas.pos.planner.persistence;

import at.spengergasse.haas.pos.planner.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findByIdentifier(String id);

    void deleteByIdentifier(String id);

}
