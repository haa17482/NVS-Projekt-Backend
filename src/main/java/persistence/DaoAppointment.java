package persistence;

import at.spengergasse.haas.pos.planner.Appointment;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoAppointment extends AbstractDao<Appointment,Long>{

    public DaoAppointment(EntityManager entityManager){
        super(entityManager);
    }

    @Override
    protected Class<Appointment> getPClass() {
        return Appointment.class;
    }



    /*private EntityManager entityManager;

    public Appointment findById(Long id) {
        return entityManager.find(Appointment.class, id);
    }

    public List<Appointment> findAll() {
        return entityManager.createQuery(
                "select a from Appointment a",
                Appointment.class)
                .getResultList();
    }
    public Appointment save(Appointment appointment){
        entityManager.persist(appointment);
        return appointment;
    }

    public void delete(Appointment appointment){
        entityManager.remove(appointment);
        appointment.setId(null);
    }*/

}

