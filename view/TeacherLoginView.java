package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import entity.Teacher;
import exception.DAOException;
import service.AuthenticationService;

public class TeacherLoginView implements View {
    private AuthenticationService authenticationService = new AuthenticationService();
    private MainView mainView;
    private Scanner scanner;
    private TeacherView teacherView;

    TeacherLoginView(MainView mainView, Scanner scanner) {
        this.mainView = mainView;
        this.scanner = scanner;
        // this.teacherView = new TeacherView(scanner, mainView);
    }

    @Override
    public void startView() {
        clearScreen();

        System.out.println("Insira o nome de usuário para login:");
        String username = scanner.nextLine();
        clearScreen();

        System.out.println("Insira sua senha:");
        String password = scanner.nextLine();
        clearScreen();

        try {
            Teacher newTeacher = authenticationService.login(new Teacher(username, password));

            clearScreen();
            System.out.println("Login realizado com sucesso.\n");

            teacherView = new TeacherView(scanner, mainView, newTeacher);
            // teacherView.setTeacher(newTeacher);
            teacherView.startView();
            /*
             * else {
             * System.out.print("\033[H\033[2J");
             * System.out.flush();
             * System.out.println("Usuário não encontrado.\n");
             * 
             * }
             */

        } catch (DAOException | AuthenticationException e) {
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
