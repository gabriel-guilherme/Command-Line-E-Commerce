package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AdminView extends UiView {
    private MainView mainView;
    private TeacherRegisterView teacherRegisterView;
    private UpdateAdminView updateAdminView;

    AdminView(MainView mainView, Scanner scanner) {
        this.scanner = scanner;
        this.mainView = mainView;
        this.updateAdminView = new UpdateAdminView(this, scanner);
        this.teacherRegisterView = new TeacherRegisterView(this, scanner);
    }

    @Override
    public void startView() {

        ArrayList<String> options = new ArrayList<>(
                Arrays.asList("Registrar professor", "Mudar palavra-chave", "Voltar"));
        String input = bakeMenu("ADMIN MENU", options);

        if (input.equals("1")) {
            clearScreen();

            teacherRegisterView.startView();
        } else if (input.equals("2")) {
            clearScreen();

            updateAdminView.startView();
        } else if (input.equals("3")) {
            clearScreen();

            mainView.startView();
        }

        scanner.close();
    }

}
