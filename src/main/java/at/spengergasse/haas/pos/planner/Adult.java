package at.spengergasse.haas.pos.planner;

import java.util.Date;

public class Adult extends Patient {
    public Adult(String firstname, String sirname, Date birthday, int age, boolean man) {
        super(firstname, sirname, birthday, age, man);
    }
}
