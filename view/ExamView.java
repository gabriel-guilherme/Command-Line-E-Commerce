package view;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Exam;
import entity.Question;
import entity.User;
import exception.DAOException;
import exception.ExamException;
import exception.StudentException;
import service.ExamService;
import service.StudentService;

public class ExamView extends UiView {
    private UiView previousView;
    private Exam exam;
    private QuestionView questionView;
    private CorrectQuestionView correctQuestionView;
    private User student;
    private StudentService studentService = new StudentService();
    private ExamService examService = new ExamService();

    public ExamView(Scanner scanner, UiView previousView, Exam exam, User student) {
        this.scanner = scanner;
        this.previousView = previousView;
        this.exam = exam;
        this.student = student;
    }

    @Override
    public void startView() {

        String input = "";
        Question question;
        ArrayList<String> options = new ArrayList<String>();

        for (int i = 0; i < exam.getQuestions().size(); i++) {
            options.add("");
        }

        try {
            if (studentService.checkExam(exam, student) && previousView instanceof StudentView) {
                throw new StudentException("Prova encerrada");
            }

            while (!input.equals("f")) {
                try {
                    // clearScreen();
                    Exam studentExam = student.getExam(exam);
                    if (studentExam != null) {
                        options.clear();
                        studentExam.getQuestions().forEach(currQuestion -> options
                                .add(currQuestion.getResponse() + " Nota: " + currQuestion.getGrade()));
                    }

                    if (previousView instanceof StudentView) {
                        input = bakeMenu("Prova " + exam.getName(), options);

                        if (input.equals("f")) {
                            clearScreen();
                            break;
                        }

                        int questionIndex = Integer.parseInt(input) - 1;

                        if (questionIndex < 0 || questionIndex >= exam.getQuestions().size()) {
                            throw new NumberFormatException();
                        }

                        question = exam.getQuestion(questionIndex);
                        questionView = new QuestionView(scanner, question, exam, student);

                        clearScreen();
                        questionView.startView();
                    } else {
                        clearScreen();
                        input = bakeMenu("Prova " + exam.getName() + " de " + student.getName(), options);

                        if (input.equals("f")) {
                            clearScreen();
                            break;
                        }

                        int questionIndex = Integer.parseInt(input) - 1;

                        if (questionIndex < 0 || questionIndex >= exam.getQuestions().size()) {
                            throw new NumberFormatException();
                        }

                        question = exam.getQuestion(questionIndex);
                        correctQuestionView = new CorrectQuestionView(scanner, question, exam, student);

                        clearScreen();
                        correctQuestionView.startView();
                    }
                } catch (NumberFormatException e) {
                    clearScreen();
                    System.out.println("Erro: Por favor, insira um número válido.\n");
                }
            }

            if (previousView instanceof StudentView) {
                exam.addStudent(student.getLogin());
                examService.updateExam(exam);

                exam.setBoolStatus(true);
                studentService.saveExam(exam, student);
            }

            System.out.println("Prova fechada.\n");
            previousView.startView();
        } catch (DAOException | StudentException | ExamException e) {
            clearScreen();
            System.out.println("Erro: " + e.getMessage() + "\n");
            previousView.startView();
        }

    }

}
