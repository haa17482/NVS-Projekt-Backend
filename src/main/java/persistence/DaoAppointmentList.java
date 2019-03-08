package persistence;

import at.spengergasse.haas.pos.planner.Appointment;
import at.spengergasse.haas.pos.planner.AppointmentList;

import javax.persistence.EntityManager;

public class DaoAppointmentList extends AbstractDao<AppointmentList, Long> {

    public DaoAppointmentList(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<AppointmentList> getPClass() {
        return AppointmentList.class;
    }

   /* private EntityManager entityManager;


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
    }*/

}
