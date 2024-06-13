package entity;

import java.util.List;

public class Exam extends Entity {
    private String name;
    private String subject;
    private List<Question> questions;

    /// CONSTRUCTOR
    public Exam(String name, String subject, List<Question> questions) {
        this.setName(name);
        this.setSubject(subject);
        this.setQuestions(questions);
    }

    /// GETTERS

    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.subject;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    /// SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Exam{" + getSubject() + " " + getName() + getQuestions() + "}";
    }
}