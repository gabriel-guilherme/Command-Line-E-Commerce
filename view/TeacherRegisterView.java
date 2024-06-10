package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import entity.Teacher;
import exception.DAOException;
import service.AuthenticationService;

public class TeacherRegisterView implements View {
    private AuthenticationService authenticationService = new AuthenticationService();
    private AdminView adminView;
    private Scanner scanner;

    TeacherRegisterView(AdminView adminView, Scanner scanner) {
        this.adminView = adminView;
        this.scanner = scanner;
    }

    @Override
    public void startView() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Insira o seu nome:");
        String name = scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Insira o nome de usuario para registro:");
        String username = scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Insira sua senha:");
        String password = scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        try {
            authenticationService.register(new Teacher(username, password, name));

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Cadastro realizado com sucesso.\n");
        } catch (DAOException e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(e.getMessage() + "\n");
        } catch (AuthenticationException e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(e.getMessage() + "\n");
        }

        adminView.startView();
    }
}
