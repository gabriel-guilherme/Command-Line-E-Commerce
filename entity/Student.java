package entity;

public class Student extends User {

    public Student(String matriculation, String register, String name) {
        super(matriculation, register, name);
    }

    public Student(String matriculation, String register) {
        super(matriculation, register);
    }
}
