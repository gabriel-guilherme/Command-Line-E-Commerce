package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import entity.User;
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

    @Override
    public void startView() {
        try {
            ArrayList<String> options = new ArrayList<>(
                    Arrays.asList("Provas", "Registrar nova matéria", "Voltar"));

            String input = bakeMenu("Bem vindo " + student.getName() + ",\n\n" + "MENU DO ALUNO", options);

            if (input.equals("1")) {

                clearScreen();
                options = studentService.listExams(student);

                int chosedExam = Integer.parseInt(bakeMenu("Escolha a prova", options));

                examView = new ExamView(scanner, this, studentService.getExams(student).get(chosedExam - 1), student);

                clearScreen();
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
            } else {
                throw new IndexOutOfBoundsException();
            }
        } catch (StudentException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
            this.startView();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            clearScreen();

            System.out.println("\"Erro: Por favor, insira um número válido.\n");
            this.startView();
        }

        // scanner.close();
    }

}
