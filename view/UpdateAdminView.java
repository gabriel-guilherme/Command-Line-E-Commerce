package view;

import java.util.Scanner;

import exception.AuthenticationException;

import service.AuthenticationService;

public class UpdateAdminView extends UiView {
    private AdminView adminView;
    private AuthenticationService authenticationService = new AuthenticationService();

    UpdateAdminView(AdminView adminView, Scanner scanner) {
        this.scanner = scanner;
        this.adminView = adminView;
    }

    @Override
    public void startView() {

        String input = bakeMenu("Insira a nova palavra-chave");

        try {
            authenticationService.updateAdmin(input);

            clearScreen();
            System.out.println("Palavra-chave modificada.\n");

            adminView.startView();
        } catch (AuthenticationException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
            adminView.startView();
        }

        // scanner.close();
    }
}
