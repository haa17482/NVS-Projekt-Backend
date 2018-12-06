package at.spengergasse.haas.pos.planner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import at.spengergasse.haas.pos.planner.Patient;

public class DaoPatient implements Dao<Patient, Long>{

    private Connection connection;

    public DaoPatient(Connection connection){
        this.connection = connection;
    }

    @Override
    public Patient findById(Long id) {
        return null;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> result = new ArrayList<Patient>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from Patient");
            while(rs.next()){
                Long id = rs.getLong("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDate birthday = rs.getObject("birthday",LocalDate.class);
                int age = rs.getInt("age");
                float height = rs.getFloat("height");
                float weight = rs.getFloat("weight");
                var type= Type.valueOf(rs.getString("type"));

                Patient r = new Patient();
                r.afterInsert(id);
                result.add(r);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Patient save(Patient persistable) {
        return null;
    }

    @Override
    public Patient delete(Patient persistable) {
        return null;
    }
}
