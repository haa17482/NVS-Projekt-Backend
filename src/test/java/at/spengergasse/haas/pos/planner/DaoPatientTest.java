package at.spengergasse.haas.pos.planner;

import org.junit.jupiter.api.*;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DaoPatientTest {

    private static DaoPatient daoPatient;
    private static Connection connection;
    @BeforeAll
    static void init() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:graph", "sa", "");
        daoPatient = new DaoPatient(connection);
    }

    @BeforeEach
    void createDatabase() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("create table patient (" +
                "id identity," +
                "firstname varchar(50)," +
                "sirname varchar(50)," +
                "birthday date," +
                "age int," +
                "height real," +
                "weight real," +
                "type varchar(50));");
    }


    @AfterEach
    void close() throws SQLException{
        connection.prepareStatement("DROP ALL OBJECTS").execute();
    }

    @AfterAll
    static void end() throws SQLException{
        connection.close();
    }

    @Test
    void testDelete(){
        Patient patient = new Patient();
        patient.createPatient("Sebastian","Haas", LocalDate.of(2000,11,18),18,1.80f,73f,Type.MAN);
        daoPatient.save(patient);
        Patient deletedObject = daoPatient.delete(patient);

        assertNull(deletedObject.getId());
    }

    @Test
    void testInsert(){

        Patient patient = new Patient();
        patient.createPatient("Sebastian","Haas", LocalDate.of(2000,11,18),
                18,1.80f,73f,Type.MAN);
        Patient savedObject = daoPatient.save(patient);

        assertNotNull(patient.getId());
        assertEquals(patient, savedObject);
    }

    @Test
    void testUpdate(){

        Patient patient = new Patient();
        patient.createPatient("Sebastian","Haas", LocalDate.of(2000,11,17),
                18,1.80f,73f,Type.MAN);
        Patient savedObject = daoPatient.save(patient);

        Long id = savedObject.getId();
        Patient patient2 = new Patient(id);
        patient2.createPatient("Sebastian","Haas", LocalDate.of(2000,11,18),
                18,1.80f,73f,Type.MAN);

        Patient updated = daoPatient.update(patient2);

        assertNotEquals(updated, savedObject);
        assertEquals(LocalDate.of(2000,11,18), updated.getBirthday());
    }

    @Test
    void testFindById(){
        Patient patient = new Patient();
        patient.createPatient("Sebastian","Haas", LocalDate.of(2000,11,18),
                18,1.80f,73f,Type.MAN);
        Long id = daoPatient.save(patient).getId();

        Patient foundObject = daoPatient.findById(id);

        assertEquals(foundObject, patient);
    }

    @Test
    void testFindAll(){
        List<Patient> patients = new ArrayList<>();

        Patient patient = new Patient();
        patient.createPatient("Sebastian","Haas", LocalDate.of(2000,11,18),
                18,1.80f,73f,Type.MAN);

        Patient patient2 = new Patient();
        patient2.createPatient("Sebastian","Haas", LocalDate.of(2000,11,18),
                18,1.80f,73f,Type.MAN);

        patients.add(patient);
        patients.add(patient2);
        daoPatient.save(patients.get(0));
        daoPatient.save(patients.get(1));

        List<Patient> returnedObjects = daoPatient.findAll();

        assertEquals(returnedObjects, patients);
    }

}