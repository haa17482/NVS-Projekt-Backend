package at.spengergasse.haas.pos.planner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import at.spengergasse.haas.pos.planner.Appointment;

public class DaoAppointment implements Dao<Appointment, Long>{

    private Connection connection;

    public DaoAppointment(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> result = new ArrayList<Appointment>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from Appointment");
            while (rs.next()) {
                Long id= rs.getLong("id");
                String title = rs.getString("title");
                int priority = rs.getInt("priority");
                LocalDate date = rs.getObject("date", LocalDate.class);
                Patient patient = rs.getObject("patient", Patient.class);


                Appointment r = new Appointment();
                r.makeAppointment(id,title,priority,date,patient);
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
        Appointment r = new Appointment();
        if(persistable.getTitle()==null){
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from Patient where id=");
            while (rs.next()) {
                Long id= rs.getLong("id");
                String title = rs.getString("title");
                int priority = rs.getInt("priority");
                LocalDate date = rs.getObject("date", LocalDate.class);
                Patient patient = rs.getObject("patient", Patient.class);

                r.makeAppointment(id,title,priority,date,patient);
                r.afterInsert(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }

    @Override
    public Appointment delete(Appointment persistable) {
        return null;
    }

    @Override
    public Appointment findById(Long idPersistable) {


        Appointment r = new Appointment();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from Appointment where id=" + idPersistable);
            while (rs.next()) {

                Long id= rs.getLong("id");
                String title = rs.getString("title");
                int priority = rs.getInt("priority");
                LocalDate date = rs.getObject("date", LocalDate.class);
                Patient patient = rs.getObject("patient", Patient.class);

                r.makeAppointment(id,title,priority,date,patient);
                r.afterInsert(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }
}

