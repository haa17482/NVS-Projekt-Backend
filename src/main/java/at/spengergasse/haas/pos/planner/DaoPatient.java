package at.spengergasse.haas.pos.planner;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoPatient implements Dao<Patient, Long> {

    private final Connection connection;

    public DaoPatient(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> result = new ArrayList<Patient>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from Patient");
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("sirname");
                LocalDate birthday = rs.getObject("birthday", LocalDate.class);
                int age = rs.getInt("age");
                float height = rs.getFloat("height");
                float weight = rs.getFloat("weight");
                var type = Type.valueOf(rs.getString("type"));

                Patient r = new Patient();
                r.createPatient(firstname, lastname, birthday, age, height, weight, type);
                r.afterInsert(id);
                result.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Patient save(Patient persistable) {
        if (persistable.getId() == null)
            return insert(persistable);
        else
            return update(persistable);
    }

    public Patient update(Patient patient) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("UPDATE patient SET firstname = ?,sirname = ?,birthday = ?,age = ?,height = ?,weight = ?,type = ? where id = ?;");
            statement.setString(1,patient.getFirstname());
            statement.setString(2,patient.getSirname());
            statement.setObject(3,patient.getBirthday());
            statement.setInt(4,patient.getAge());
            statement.setFloat(5,patient.getHeight());
            statement.setFloat(6,patient.getWeight());
            statement.setString(7,patient.getType().toString());
            statement.setLong(8, patient.getId());
            int rs = statement.executeUpdate();
            if (rs != 1) {
                throw new RuntimeException("Kein Update wurde gemacht");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return patient;
    }

    public Patient insert(Patient patient) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("INSERT into patient(firstname,sirname,birthday,age,height,weight,type)VALUES(?,?,?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);

            statement.setString(1,patient.getFirstname());
            statement.setString(2,patient.getSirname());
            statement.setObject(3,patient.getBirthday());
            statement.setInt(4,patient.getAge());
            statement.setFloat(5,patient.getHeight());
            statement.setFloat(6,patient.getWeight());
            statement.setString(7,patient.getType().toString());
            int rs = statement.executeUpdate();
            if (rs != 1) {
                throw new RuntimeException("Kein Insert wurde gemacht");
            } else {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    patient.afterInsert(generatedKeys.getLong(1));
                    return patient;
                } else
                    throw new RuntimeException("Kein Key");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Patient delete(Patient persistable) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int rs = statement.executeUpdate("DELETE from Patient where id=" + persistable.getId());
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
    public Patient findById(Long idPersistable) {


        Patient r = new Patient();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from Patient where id=" + idPersistable);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("sirname");
                LocalDate birthday = rs.getObject("birthday", LocalDate.class);
                int age = rs.getInt("age");
                float height = rs.getFloat("height");
                float weight = rs.getFloat("weight");
                var type = Type.valueOf(rs.getString("type"));


                r.createPatient(firstname, lastname, birthday, age, height, weight, type);
                r.afterInsert(id);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }
}
