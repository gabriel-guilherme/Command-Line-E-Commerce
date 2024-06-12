package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import entity.Student;
import exception.DAOException;
import service.AuthenticationService;

public class StudentLoginView implements View {
    private AuthenticationService authenticationService = new AuthenticationService();
    private MainView mainView;
    private Scanner scanner;
    private StudentView studentView;

    StudentLoginView(MainView mainView, Scanner scanner) {
        this.mainView = mainView;
        this.scanner = scanner;
        this.studentView = new StudentView(scanner, mainView);
    }

    @Override
    public void startView() {
        clearScreen();

        System.out.println("Insira a matricula para login:");
        String matriculation = scanner.nextLine();
        clearScreen();

        System.out.println("Insira sua senha:");
        String password = scanner.nextLine();
        clearScreen();

        try {
            Student newStudent = authenticationService.login(new Student(matriculation, password));

            clearScreen();
            System.out.println("Login realizado com sucesso.\n");

            studentView.setStudent(newStudent);
            studentView.startView();
            /*
             * else {
             * System.out.print("\033[H\033[2J");
             * System.out.flush();
             * System.out.println("Usuário não encontrado.\n");
             * 
             * }
             */

        } catch (AuthenticationException | DAOException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
            mainView.startView();
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
