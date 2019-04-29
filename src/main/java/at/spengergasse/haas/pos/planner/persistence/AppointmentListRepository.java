package at.spengergasse.haas.pos.planner.persistence;

import at.spengergasse.haas.pos.planner.model.AppointmentList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentListRepository extends JpaRepository<AppointmentList,Long> {
    Optional<AppointmentList> findByIdentifier(String id);

}
