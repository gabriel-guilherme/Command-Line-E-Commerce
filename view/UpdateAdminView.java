package view;

import java.util.Scanner;

import exception.DAOException;
import service.AuthenticationService;

public class UpdateAdminView implements View {
    private Scanner scanner;
    private AdminView adminView;
    private AuthenticationService authenticationService = new AuthenticationService();

    UpdateAdminView(AdminView adminView, Scanner scanner) {
        this.scanner = scanner;
        this.adminView = adminView;
    }

    @Override
    public void startView() {
        // System.out.print("\033[H\033[2J");
        // System.out.flush();

        System.out.println("Insira a nova palavra-chave.\n");

        String input = scanner.nextLine();

        try {
            authenticationService.updateAdmin(input);

            clearScreen();
            System.out.println("Palavra-chave modificada.\n");

            adminView.startView();
        } catch (DAOException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
            adminView.startView();
        }

        scanner.close();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
