package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Exam extends Entity {
    private String name;
    private String subject;
    private List<Question> questions;
    private boolean isClosed = false;
    private ArrayList<String> students;
    // private float grade;
    // private float value;

    /// CONSTRUCTOR
    public Exam(String name, String subject, List<Question> questions) {
        this.setName(name);
        this.setSubject(subject);
        this.setQuestions(questions);
    }

    public void addStudent(String student) {
        if (students == null) {
            students = new ArrayList<String>();
        }
        students.add(student);
    }

    /// GETTERS

    public float getGrade() {
        return questions.stream()
                .map(Question::getGrade)
                .reduce(0.0f, Float::sum);
    }

    public float getValue() {
        return questions.stream()
                .map(Question::getValue)
                .reduce(0.0f, Float::sum);
    }

    public ArrayList<String> getStudents() {
        if (students == null) {
            students = new ArrayList<String>();
        }
        return students;
    }

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

    public void setStudents(ArrayList<String> students) {
        this.students = students;
    }

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

    @Override
    public String toString() {
        return getName() + " | " + getSubject() + " | Nota: " + getGrade() + " de " + getValue();
    }

}