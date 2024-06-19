package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainView extends UiView {
    private LoginView loginView;
    private RegisterView registerView;
    // private TeacherLoginView teacherLoginView;
    private PassphraseView passphraseView;

    public MainView(Scanner scanner) {
        this.scanner = scanner;
        this.loginView = new LoginView(this, scanner);
        this.registerView = new RegisterView(this, scanner);
        // this.teacherLoginView = new TeacherLoginView(this, scanner);
        this.passphraseView = new PassphraseView(this, scanner);
    }

    @Override
    public void startView() {

        ArrayList<String> options = new ArrayList<>(Arrays.asList("Entrar",
                "Criar nova conta", "Admin", "Sair"));

        String input = bakeMenu("MENU PRINCIPAL", options);

        try {
            if (input.equals("1")) {
                clearScreen();
                loginView.startView();
            } else if (input.equals("2")) {
                clearScreen();
                registerView.startView();
            } else if (input.equals("3")) {
                clearScreen();
                passphraseView.startView();
            } else if (input.equals("4")) {
                clearScreen();

            } else {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            clearScreen();
            System.out.println("\"Erro: Por favor, insira um número válido.\n");

            this.startView();
        }

        // scanner.close();
    }

}
