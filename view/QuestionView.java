package view;

import java.util.Scanner;

import entity.Exam;
import entity.Question;
import entity.User;
import exception.StudentException;
import service.StudentService;

public class QuestionView extends UiView {
    // private Scanner scanner;
    private Question question;
    private StudentService studentService = new StudentService();
    private Exam exam;
    private User student;

    public QuestionView(Scanner scanner, Question question, Exam exam, User student) {
        this.scanner = scanner;
        this.question = question;
        this.exam = exam;
        this.student = student;
    }

    @Override
    public void startView() {
        String input;
        try {

            if (question.getType()) {

                input = bakeMenu(question.getDescription(), question.getAlternatives());

                question.setResponse(input);

                studentService.updateQuestion(exam, question, student);

                clearScreen();
                return;
            }

            input = bakeMenu(question.getDescription());

            question.setResponse(input);

            studentService.updateQuestion(exam, question, student);
            clearScreen();
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }

}
