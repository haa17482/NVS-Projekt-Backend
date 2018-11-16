package at.spengergasse.haas.pos.planner;

import java.time.LocalDate;


public class Appointment {

    private String title;
    private int priority;
    private LocalDate date;
    private boolean solved;

    public Appointment() {
        this.title = title;
        this.priority = priority;
        this.date = date;
        this.solved = solved;
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

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public void makeAppointment(String title, int priority, LocalDate date){
        System.out.println(title+" "+priority+" "+date);

    }
}
