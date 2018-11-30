package at.spengergasse.haas.pos.planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {
    private String firstname;
    private String sirname;
    private LocalDate birthday;
    private int age;
    private float height, weight;
    Type type;

    public Patient() {
        this.firstname = firstname;
        this.sirname = sirname;
        this.birthday = birthday;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.type = type;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void createPatient(String firstname, String sirname, LocalDate birthday, int age,float height,float weight, Type type){
        setFirstname(firstname);
        setSirname(sirname);
        setBirthday(birthday);
        setAge(age);
        setHeight(height);
        setWeight(weight);
        setType(type);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d.MM.uuuu");
        String text = getBirthday().format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        return "Patient Information:\n  Firstname: "+getFirstname()+ " \n  Sirname: "
                +getSirname()+" \n  Birthday: "+parsedDate.format(formatters)+" \n  Age: "+getAge()+
                " \n Height: "+getHeight()+"\n Weight: "+getWeight()+"\n Type: "+getType();

    }
}
