package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import entity.Teacher;
import exception.DAOException;
import service.AuthenticationService;

public class TeacherRegisterView extends UiView {
    private AuthenticationService authenticationService = new AuthenticationService();
    private AdminView adminView;

    TeacherRegisterView(AdminView adminView, Scanner scanner) {
        this.adminView = adminView;
        this.scanner = scanner;
    }

    @Override
    public void startView() {
        clearScreen();
        String name = bakeMenu("Insira o seu nome");

        clearScreen();
        String username = bakeMenu("Insira o nome de usuario para registro:");

        clearScreen();
        String password = bakeMenu("Insira sua senha:");

        clearScreen();

        try {
            authenticationService.register(new Teacher(username, password, name));

            clearScreen();
            System.out.println("Cadastro realizado com sucesso.\n");
        } catch (DAOException | AuthenticationException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
        }

        adminView.startView();
    }

}
