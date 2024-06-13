package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import entity.User;
import exception.DAOException;
import service.AuthenticationService;

public class LoginView extends UiView {
    private AuthenticationService authenticationService = new AuthenticationService();
    private MainView mainView;
    private UiView userView;

    LoginView(MainView mainView, Scanner scanner) {
        this.mainView = mainView;
        this.scanner = scanner;

    }

    @Override
    public void startView() {
        try {
            clearScreen();
            String matriculation = bakeMenu("Insira seu login");

            clearScreen();
            String password = bakeMenu("Insira sua senha:");

            clearScreen();

            User newUser = authenticationService.login(new User(matriculation, password, "", false));

            clearScreen();
            System.out.println("Login realizado com sucesso.\n");

            if (newUser.getIsTeacher()) {
                this.userView = new TeacherView(scanner, mainView, newUser);
            } else {
                this.userView = new StudentView(scanner, mainView, newUser);
            }

            userView.startView();

        } catch (AuthenticationException | DAOException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
            mainView.startView();
        }
    }
}
