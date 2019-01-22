package at.spengergasse.haas.pos.planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Patient implements Persistable<Long>{
    private String firstname;
    private String sirname;
    private LocalDate birthday;
    private int age;
    private float height, weight;
    private Long id;
    Type type;

    public Patient() {
    }

    public Patient(Long id) {
        this.id = id;
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

    @Override
    public Long getId() {
        return id;
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
    public void afterInsert(Long id) {
        this.id=id;
    }

    @Override
    public void afterDelete() {
    id=null;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstname='" + firstname + '\'' +
                ", sirname='" + sirname + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", id=" + id +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return age == patient.age &&
                Float.compare(patient.height, height) == 0 &&
                Float.compare(patient.weight, weight) == 0 &&
                firstname.equals(patient.firstname) &&
                sirname.equals(patient.sirname) &&
                birthday.equals(patient.birthday) &&
                Objects.equals(id, patient.id) &&
                type == patient.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, sirname, birthday, age, height, weight, id, type);
    }
}
