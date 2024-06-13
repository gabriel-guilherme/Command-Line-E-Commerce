package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import dao.ExamDao;
import entity.Exam;
import entity.User;
import exception.DAOException;
import exception.StudentException;
import service.StudentService;

public class StudentView extends UiView {
    private User student;
    // private Scanner scanner;
    private MainView mainView;
    private ExamView examView;
    private StudentService studentService = new StudentService();

    StudentView(Scanner scanner, MainView mainView, User student) {
        this.scanner = scanner;
        this.mainView = mainView;
        this.student = student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getStudent() {
        return student;
    }

    @Override
    public void startView() {
        try {
            ArrayList<String> options = new ArrayList<>(
                    Arrays.asList("Provas", "Registrar nova matéria", "Voltar"));

            String input = bakeMenu("BEM VINDO " + student.getName() + " MENU DO ALUNO", options);

            if (input.equals("1")) {
                // studentService.listExams(student);

                ExamDao examDao = new ExamDao();

                List<Exam> exams = examDao.findAll().stream().collect(Collectors.toList());

                clearScreen();

                options = (ArrayList<String>) examDao.findAll().stream().map(Exam::toString)
                        .collect(Collectors.toList());

                int chosedExam = Integer.parseInt(bakeMenu("Escolha a prova", options));

                examView = new ExamView(scanner, this, exams.get(chosedExam - 1));

                examView.startView();
            } else if (input.equals("2")) {

                clearScreen();

                input = bakeMenu("Informe a matéria que você quer registrar:");

                studentService.register(student, input);
                clearScreen();

                this.startView();
            } else if (input.equals("3")) {
                clearScreen();

                mainView.startView();
            }
        } catch (DAOException | StudentException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
        }

        scanner.close();
    }

}
