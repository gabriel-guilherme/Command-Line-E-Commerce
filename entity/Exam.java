package entity;

import java.util.List;
import java.util.Optional;

public class Exam extends Entity {
    private String name;
    private String subject;
    private List<Question> questions;
    private boolean isClosed = false;

    /// CONSTRUCTOR
    public Exam(String name, String subject, List<Question> questions) {
        this.setName(name);
        this.setSubject(subject);
        this.setQuestions(questions);
    }

    /// GETTERS

    public boolean getBoolStatus() {
        return isClosed;
    }

    public String getStatus() {
        if (!isClosed) {
            return "Aberta";
        }

        return "Fechada";
    }

    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.subject;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public Question getQuestion(int i) {
        return this.questions.get(i);
    }

    public Question getQuestion(Question question) {
        Optional<Question> foundQuestion = this.questions.stream()
                .filter(currQuestion -> currQuestion.getDescription().equals(question.getDescription()))
                .findFirst();
        return foundQuestion.orElse(null);
    }

    /// SETTERS

    public void setBoolStatus(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    // public void setQuestions(Question question, int i) {
    // this.questions.get(i).put(question);
    // }

    @Override
    public String toString() {
        return getName() + " | " + getSubject() + " | " + getStatus();
        // return "Exam{" + getSubject() + " " + getName() + getQuestions() + "}";
    }

}