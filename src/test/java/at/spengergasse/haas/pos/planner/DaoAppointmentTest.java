package at.spengergasse.haas.pos.planner;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DaoAppointmentTest {


    private static DaoAppointment daoAppointment;
    private static Connection connection;
    private static DaoPatient daoPatient;

    @BeforeAll
    static void init() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:graph", "sa", "");
        daoPatient = new DaoPatient(connection);
        daoAppointment = new DaoAppointment(connection, daoPatient);
    }

    @BeforeEach
    void createDatabase() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table appointment (" +
                "id identity," +
                "title varchar(50)," +
                "priority int," +
                "date date," +
                "patient BIGINT );");

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
    void close() throws SQLException {
        connection.prepareStatement("DROP ALL OBJECTS").execute();
    }

    @AfterAll
    static void end() throws SQLException {
        connection.close();
    }

    @Test
    void testDelete() {
        Appointment appointment = new Appointment();
        Patient patient1 = new Patient();
        patient1.createPatient("Sebastian", "Haas", LocalDate.of(2000, 11, 18), 18, 1.80f, 73f, Type.MAN);

        appointment.makeAppointment("Untersuchung", 1, LocalDate.of(2000, 11, 18), patient1);
        daoPatient.save(patient1);
        daoAppointment.save(appointment);
        Appointment deletedObject = daoAppointment.delete(appointment);

        assertNull(deletedObject.getId());
    }

    @Test
    void testInsert() {
        Appointment appointment = new Appointment();
        Patient patient = new Patient();
        patient.createPatient("Sebastian", "Haas", LocalDate.of(2000, 11, 18),
                18, 1.80f, 73f, Type.MAN);
        daoPatient.save(patient);
        appointment.makeAppointment("Untersuchung", 1, LocalDate.of(2000, 11, 18), patient);
        Appointment savedObject = daoAppointment.save(appointment);

        assertNotNull(savedObject.getId());
        assertEquals(appointment, savedObject);
    }

    @Test
    void testUpdate() {
        Patient patient = new Patient();
        patient.createPatient("Sebastian", "Haas", LocalDate.of(2000, 11, 17),
                18, 1.80f, 73f, Type.MAN);
        daoPatient.save(patient);

        Appointment appointment = new Appointment();
        appointment.makeAppointment("Untersuchung", 1, LocalDate.of(2000, 11, 18), patient);
        Appointment savedObject = daoAppointment.save(appointment);

        Long id = savedObject.getId();
        Appointment appointment2 = new Appointment(id);

        Patient patient2 = new Patient();
        patient2.createPatient("Sebastian", "Haas", LocalDate.of(2000, 11, 18),
                18, 1.80f, 73f, Type.MAN);
        daoPatient.save(patient2);

        appointment2.makeAppointment( "Untersuchung", 1, LocalDate.of(2000, 11, 17), patient2);

        Appointment updated = daoAppointment.update(appointment2);

        assertNotEquals(updated, savedObject);
        assertEquals(LocalDate.of(2000, 11, 17), updated.getDate());
        assertEquals(LocalDate.of(2000, 11, 18), updated.getPatient().getBirthday());
    }

    @Test
    void testFindById() {
        Appointment appointment = new Appointment();
        Patient patient = new Patient();
        patient.createPatient("Sebastian", "Haas", LocalDate.of(2000, 11, 18),
                18, 1.80f, 73f, Type.MAN);
        daoPatient.save(patient);
        appointment.makeAppointment("Untersuchung", 1, LocalDate.of(2000, 11, 18), patient);

        Long id = daoAppointment.save(appointment).getId();

        Appointment foundObject = daoAppointment.findById(id);

        assertEquals(foundObject, appointment);
    }

    @Test
    void testFindAll() {
        List<Appointment> appointments = new ArrayList<>();

        Patient patient = new Patient();
        patient.createPatient("Sebastian", "Haas", LocalDate.of(2000, 11, 18),
                18, 1.80f, 73f, Type.MAN);

        Patient patient2 = new Patient();
        patient2.createPatient("Sebastian", "Haas", LocalDate.of(2000, 11, 18),
                18, 1.80f, 73f, Type.MAN);

        daoPatient.save(patient);
        daoPatient.save(patient2);

        Appointment appointment = new Appointment();
        Appointment appointment2 = new Appointment();
        appointment.makeAppointment("Untersuchung", 1, LocalDate.of(2000, 11, 18), patient);
        appointment2.makeAppointment("KeineAhnung", 1, LocalDate.of(2000, 12, 18), patient2);

        appointments.add(appointment);
        appointments.add(appointment2);
        daoAppointment.save(appointments.get(0));
        daoAppointment.save(appointments.get(1));

        List<Appointment> returnedObjects = daoAppointment.findAll();

        assertEquals(returnedObjects, appointments);
    }

}
