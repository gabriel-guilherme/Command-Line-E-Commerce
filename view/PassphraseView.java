package view;

import java.util.Scanner;

import javax.naming.AuthenticationException;

import exception.DAOException;
import service.AuthenticationService;

public class PassphraseView implements View {
    private Scanner scanner;
    private MainView mainView;
    private AdminView adminView;
    private AuthenticationService authenticationService = new AuthenticationService();

    PassphraseView(MainView mainView, Scanner scanner) {
        this.scanner = scanner;
        this.mainView = mainView;
        this.adminView = new AdminView(mainView, scanner);
    }

    @Override
    public void startView() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("ADMIN\n\nInsira a senha.\n");

        String input = scanner.nextLine();

        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            authenticationService.admin(input);
            adminView.startView();
        } catch (AuthenticationException e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(e.getMessage() + "\n");
            mainView.startView();
        } catch (DAOException e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(e.getMessage() + "\n");
            mainView.startView();
        }

        scanner.close();
    }

}
