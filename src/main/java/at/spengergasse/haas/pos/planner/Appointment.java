package at.spengergasse.haas.pos.planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Appointment implements Persistable<Long>{

    private Long id;
    private String title;
    private int priority;
    private LocalDate date;
    Patient patient;


    public Appointment() {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.date = date;
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

    @Override
    public String toString() {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d.MM.uuuu");
        String text = getDate().format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);

        return "Appointment Information:\n"+getId()+"\n Title: "+getTitle()+ " \n Priority: "+getPriority()+" \n Date: "+parsedDate.format(formatters)+" \n Patient: "+patient.toString();
    }

    @Override
    public void afterInsert(Long id ) {
        this.title=title;
    }

    @Override
    public void afterDelete() {
        title=null;
    }
}
