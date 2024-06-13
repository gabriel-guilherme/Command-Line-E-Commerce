package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import entity.Student;
import exception.DAOException;
import service.AuthenticationService;

public class StudentRegisterView extends UiView {
    private AuthenticationService authenticationService = new AuthenticationService();
    private MainView mainView;
    // private Scanner scanner;

    StudentRegisterView(MainView mainView, Scanner scanner) {
        this.mainView = mainView;
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
            authenticationService.register(new Student(matriculation, password, name));

            clearScreen();
            System.out.println("Cadastro realizado com sucesso.\n");
        } catch (DAOException | AuthenticationException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
        }

        mainView.startView();
    }
}
