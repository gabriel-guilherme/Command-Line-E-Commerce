package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import entity.Teacher;
import exception.DAOException;
import service.AuthenticationService;

public class TeacherLoginView extends UiView {
    private AuthenticationService authenticationService = new AuthenticationService();
    private MainView mainView;
    private TeacherView teacherView;

    TeacherLoginView(MainView mainView, Scanner scanner) {
        this.mainView = mainView;
        this.scanner = scanner;
    }

    @Override
    public void startView() {
        clearScreen();
        String username = bakeMenu("Insira o nome de usuário para login:");

        clearScreen();
        String password = bakeMenu("Insira sua senha:");

        clearScreen();

        try {
            Teacher newTeacher = authenticationService.login(new Teacher(username, password));

            clearScreen();
            System.out.println("Login realizado com sucesso.\n");

            teacherView = new TeacherView(scanner, mainView, newTeacher);

            teacherView.startView();

        } catch (DAOException | AuthenticationException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
            mainView.startView();
        }
    }

}
