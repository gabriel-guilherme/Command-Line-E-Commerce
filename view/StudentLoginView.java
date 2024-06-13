package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import entity.Student;
import exception.DAOException;
import service.AuthenticationService;

public class StudentLoginView extends UiView {
    private AuthenticationService authenticationService = new AuthenticationService();
    private MainView mainView;
    private StudentView studentView;

    StudentLoginView(MainView mainView, Scanner scanner) {
        this.mainView = mainView;
        this.scanner = scanner;
        this.studentView = new StudentView(scanner, mainView);
    }

    @Override
    public void startView() {
        clearScreen();
        String matriculation = bakeMenu("Insira a matricula para login");

        clearScreen();
        String password = bakeMenu("Insira sua senha:");

        clearScreen();

        try {
            Student newStudent = authenticationService.login(new Student(matriculation, password));

            clearScreen();
            System.out.println("Login realizado com sucesso.\n");

            studentView.setStudent(newStudent);
            studentView.startView();

        } catch (AuthenticationException | DAOException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
            mainView.startView();
        }
    }
}
