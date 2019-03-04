package persistence;

import at.spengergasse.haas.pos.planner.AppointmentList;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
public class DaoAppointmentList {

    private EntityManager entityManager;


    public AppointmentList findById(Long id) {
        return entityManager.find(AppointmentList.class, id);
    }

    public List<AppointmentList> findAll() {
        return entityManager.createQuery(
                "select al from AppointmentList al",
                AppointmentList.class).
                getResultList();
    }

    public AppointmentList save(AppointmentList appointmentList){
         entityManager.persist(appointmentList);
         return appointmentList;
    }
    public void delete(AppointmentList appointmentList){
        entityManager.remove(appointmentList);
        appointmentList.setId(null);
    }

}
