package view;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Exam;
import entity.Question;
import entity.User;
import exception.DAOException;
import exception.StudentException;
import service.StudentService;

public class ExamView extends UiView {
    private StudentView studentView;
    private Exam exam;
    private QuestionView questionView;
    private User student;
    private StudentService studentService = new StudentService();

    public ExamView(Scanner scanner, StudentView studentView, Exam exam, User student) {
        this.scanner = scanner;
        this.studentView = studentView;
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

        System.out.println(student.getExams() + "\nAAAAAAAAA");

        try {
            if (studentService.checkExam(exam, student)) {
                throw new StudentException("Prova encerrada");
            }

            while (!input.equals("f")) {
                // clearScreen();
                Exam studentExam = student.getExam(exam);
                if (studentExam != null) {
                    options.clear();
                    studentExam.getQuestions().forEach(currQuestion -> options.add(currQuestion.getResponse()));
                }

                input = bakeMenu("Prova " + exam.getName(), options);

                if (input.equals("f")) {
                    clearScreen();
                    break;
                }

                try {
                    int questionIndex = Integer.parseInt(input) - 1;

                    if (questionIndex < 0 || questionIndex >= exam.getQuestions().size()) {
                        throw new NumberFormatException();
                    }

                    question = exam.getQuestion(questionIndex);
                    questionView = new QuestionView(scanner, question, exam, student);

                    clearScreen();
                    questionView.startView();
                } catch (NumberFormatException e) {
                    clearScreen();
                    System.out.println("Erro: Por favor, insira um número válido.\n");
                }
            }

            exam.setBoolStatus(true);
            studentService.saveExam(exam, student);

            System.out.println("Prova fechada.\n");
            studentView.startView();
        } catch (DAOException | StudentException e) {
            clearScreen();
            System.out.println("Erro: " + e.getMessage() + "\n");
            studentView.startView();
        }

    }

}
