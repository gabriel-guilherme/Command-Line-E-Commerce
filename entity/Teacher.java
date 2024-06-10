package entity;

public class Teacher extends User {

    public Teacher(String matriculation, String register, String name) {
        super(matriculation, register, name);
    }

    public Teacher(String matriculation, String register) {
        super(matriculation, register);
    }
}
