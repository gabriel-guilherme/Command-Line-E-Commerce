package view;

import java.util.Scanner;

import entity.Exam;
import entity.Question;
import entity.User;
import exception.ExamException;
import exception.StudentException;
import service.StudentService;

public class CorrectQuestionView extends UiView {
    // private Scanner scanner;
    private Question question;
    private StudentService studentService = new StudentService();
    private Exam exam;
    private User student;

    public CorrectQuestionView(Scanner scanner, Question question, Exam exam, User student) {
        this.scanner = scanner;
        this.question = question;
        this.exam = exam;
        this.student = student;
    }

    @Override
    public void startView() {
        float input;
        try {

            if (question.getType()) {
                return;
            }

            input = Float
                    .parseFloat(
                            bakeMenu("Questão: " + question.getDescription() + "           Vale: " + question.getValue()
                                    + "\n\nResposta: " + question.getResponse()));

            if (input > question.getValue()) {
                throw new ExamException("Pontuação maior do que a nota possível");
            }
            question.setGrade(input);

            studentService.updateQuestion(exam, question, student);
            clearScreen();
        } catch (StudentException | ExamException e) {
            clearScreen();
            System.out.println(e.getMessage());
            this.startView();
        } catch (NumberFormatException e) {
            clearScreen();
            System.out.println("\"Erro: Por favor, insira um número válido.\n");
            this.startView();
        }
    }

}
