package entity;

import java.util.List;

public class User extends Entity {
    private String name;
    private String login;
    private String password;
    private List<String> subjects;

    /// CONSTRUCTOR
    public User(String login, String password, String name) {
        this.setLogin(login);
        this.setPassword(password);
        this.setName(name);
    }

    public User(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }

    public void addSubject(String subject) {
        subjects.add(subject);
    }

    /// GETTERS

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public List<String> getSubjects() {
        return this.subjects;
    }

    /// SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "User{" + getName() + getLogin() + getSubjects() + getId() + "}";
    }
}