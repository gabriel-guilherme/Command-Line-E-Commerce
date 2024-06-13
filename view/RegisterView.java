package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import entity.User;
import exception.DAOException;
import service.AuthenticationService;

public class RegisterView extends UiView {
    private AuthenticationService authenticationService = new AuthenticationService();
    private UiView previousView;
    // private Scanner scanner;

    RegisterView(UiView previousView, Scanner scanner) {
        this.previousView = previousView;
        this.scanner = scanner;
    }

    @Override
    public void startView() {
        clearScreen();
        String name = bakeMenu("Insira o seu nome:");

        clearScreen();
        String matriculation = bakeMenu("Insira a matricula para registro:");

        clearScreen();
        String password = bakeMenu("Insira sua senha:");

        clearScreen();

        try {
            if (previousView instanceof AdminView) {
                authenticationService.register(new User(matriculation, password, name, true));
            } else {
                authenticationService.register(new User(matriculation, password, name, false));
            }

            clearScreen();
            System.out.println("Cadastro realizado com sucesso.\n");
        } catch (DAOException | AuthenticationException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
        }

        previousView.startView();
    }
}
