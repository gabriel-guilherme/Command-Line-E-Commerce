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
    private TeacherView TeacherView;

    TeacherLoginView(MainView mainView, Scanner scanner) {
        this.mainView = mainView;
        this.scanner = scanner;
        this.TeacherView = new TeacherView(scanner, mainView);
    }

    @Override
    public void startView() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Insira o nome de usuário para login:");
        String username = scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Insira sua senha:");
        String password = scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        try {
            Teacher newTeacher = authenticationService.login(new Teacher(username, password));

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Login realizado com sucesso.\n");

            TeacherView.setTeacher(newTeacher);
            TeacherView.startView();
            /*
             * else {
             * System.out.print("\033[H\033[2J");
             * System.out.flush();
             * System.out.println("Usuário não encontrado.\n");
             * 
             * }
             */

        } catch (DAOException e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(e.getMessage() + "\n");
        } catch (AuthenticationException e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(e.getMessage() + "\n");
            mainView.startView();
        }
    }
}
