package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AdminView extends UiView {
    private MainView mainView;
    private RegisterView registerView;
    private UpdateAdminView updateAdminView;

    AdminView(MainView mainView, Scanner scanner) {
        this.scanner = scanner;
        this.mainView = mainView;
        this.updateAdminView = new UpdateAdminView(this, scanner);
        this.registerView = new RegisterView(this, scanner);
    }

    @Override
    public void startView() {

        ArrayList<String> options = new ArrayList<>(
                Arrays.asList("Registrar professor", "Mudar palavra-chave", "Voltar"));
        String input = bakeMenu("ADMIN MENU", options);

        try {
            if (input.equals("1")) {
                clearScreen();

                registerView.startView();
            } else if (input.equals("2")) {
                clearScreen();

                updateAdminView.startView();
            } else if (input.equals("3")) {
                clearScreen();

                mainView.startView();
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
