package at.spengergasse.haas.pos.planner;

import java.util.Date;

public class Patient {
    private String firstname;
    private String sirname;
    private Date birthday;
    private int age;
    private boolean man;

    public Patient(String firstname, String sirname, Date birthday, int age, boolean man) {
        this.firstname = firstname;
        this.sirname = sirname;
        this.birthday = birthday;
        this.age = age;
        this.man = man;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSirname() {
        return sirname;
    }

    public void setSirname(String sirname) {
        this.sirname = sirname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }


}
