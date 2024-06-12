package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import entity.Student;
import exception.DAOException;
import service.AuthenticationService;

public class StudentRegisterView implements View {
    private AuthenticationService authenticationService = new AuthenticationService();
    private MainView mainView;
    private Scanner scanner;

    StudentRegisterView(MainView mainView, Scanner scanner) {
        this.mainView = mainView;
        this.scanner = scanner;
    }

    @Override
    public void startView() {
        clearScreen();

        System.out.println("Insira o seu nome:");
        String name = scanner.nextLine();
        clearScreen();

        System.out.println("Insira a matricula para registro:");
        String matriculation = scanner.nextLine();
        clearScreen();

        System.out.println("Insira sua senha:");
        String password = scanner.nextLine();
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

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
