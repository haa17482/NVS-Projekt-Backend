package at.spengergasse.haas.pos.planner.persistence;

import at.spengergasse.haas.pos.planner.model.AppointmentList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentListRepository extends CrudRepository<AppointmentList,Long> {
}
