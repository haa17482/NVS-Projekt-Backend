package at.spengergasse.haas.pos.planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Appointment implements Persistable<Long>{

    private Long id;
    private String title;
    private int priority;
    private LocalDate date;
    Patient patient;


    public Appointment(){}

    public Appointment(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void makeAppointment(Long id,String title, int priority, LocalDate date, Patient patient){
        setId(id);
        setTitle(title);
        setPriority(priority);
        setDate(date);
        setPatient(patient);
    }

    public void makeAppointment(String title, int priority, LocalDate date, Patient patient){
        makeAppointment(id, title, priority, date, patient);
    }

    @Override
    public void afterInsert(Long id ) {
        this.id = id;
    }

    @Override
    public void afterDelete() {
        id=null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return priority == that.priority &&
                Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(date, that.date) &&
                Objects.equals(patient, that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, priority, date, patient);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", date=" + date +
                ", patient=" + patient +
                '}';
    }
}
