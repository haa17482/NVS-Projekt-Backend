package at.spengergasse.haas.pos.planner;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DaoAppointment {

    private EntityManager entityManager;

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
    }



   /* private final DaoPatient daoPatient;
    private final Connection connection;


    public DaoAppointment(Connection connection, DaoPatient daoPatient) {
        this.connection = connection;
        this.daoPatient = daoPatient;
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> result = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from Appointment");
            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                int priority = rs.getInt("priority");
                LocalDate date = rs.getObject("date", LocalDate.class);
                Long patientid = rs.getLong("patient");
                Patient patient = daoPatient.findById(patientid);

                Appointment r = new Appointment();
                r.makeAppointment(id, title, priority, date, patient);
                r.afterInsert(id);
                result.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Appointment save(Appointment persistable) {
        if (persistable.getId() == null)
            return insert(persistable);
        else
            return update(persistable);
    }

    public Appointment update(Appointment appointment) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("UPDATE appointment SET title = ?,priority = ?,date = ?,patient = ? where id = ?;");
            statement.setString(1,appointment.getTitle());
            statement.setInt(2,appointment.getPriority());
            statement.setObject(3,appointment.getDate());
            statement.setLong(4,appointment.getPatient().getId());
            statement.setLong(5, appointment.getId());
            int rs = statement.executeUpdate();
            if (rs != 1) {
                throw new RuntimeException("Kein Update wurde gemacht");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return appointment;
    }


    public Appointment insert(Appointment appointment) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("INSERT into appointment(title,priority,date,patient)VALUES(?,?,?,?);",Statement.RETURN_GENERATED_KEYS);

            statement.setString(1,appointment.getTitle());
            statement.setInt(2,appointment.getPriority());
            statement.setObject(3,appointment.getDate());
            statement.setLong(4,appointment.getPatient().getId());
            int rs = statement.executeUpdate();
            if (rs != 1) {
                throw new RuntimeException("Kein Insert wurde gemacht");
            } else {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    appointment.afterInsert(generatedKeys.getLong(1));
                    return appointment;
                } else
                    throw new RuntimeException("Kein Key");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Appointment delete(Appointment persistable) {
        Statement statement;
        try {
            statement = connection.createStatement();
            int rs = statement.executeUpdate("DELETE from Appointment where id=" + persistable.getId());
            if (rs == 1) {
                persistable.afterDelete();
                return persistable;
            } else
                throw new RuntimeException("Update hat nix verändert!");

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Appointment findById(Long idPersistable) {
        Appointment r = new Appointment();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from Appointment where id=" + idPersistable);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                int priority = rs.getInt("priority");
                LocalDate date = rs.getObject("date", LocalDate.class);
                Long patientid = rs.getLong("patient");
                Patient patient = daoPatient.findById(patientid);

                r.makeAppointment(id, title, priority, date, patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }*/
}

