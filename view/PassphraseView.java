package view;

import java.util.Scanner;

import service.AuthenticationService;

public class PassphraseView extends UiView {
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
        clearScreen();

        String input = bakeMenu("ADMIN\n\nInsira a senha");

        try {
            clearScreen();

            authenticationService.admin(input);
            adminView.startView();
        } catch (exception.AuthenticationException e) {
            clearScreen();

            System.out.println(e.getMessage() + "\n");
            mainView.startView();
        }

        // scanner.close();
    }

}
