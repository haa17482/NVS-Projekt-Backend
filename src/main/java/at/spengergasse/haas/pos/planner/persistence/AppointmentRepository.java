package at.spengergasse.haas.pos.planner.persistence;

import at.spengergasse.haas.pos.planner.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findByIdentifier(String id);

}
