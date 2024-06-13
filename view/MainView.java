package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainView extends UiView {
    private StudentLoginView studentLoginView;
    private StudentRegisterView studentRegisterView;
    private TeacherLoginView teacherLoginView;
    private PassphraseView passphraseView;

    public MainView(Scanner scanner) {
        this.scanner = scanner;
        this.studentLoginView = new StudentLoginView(this, scanner);
        this.studentRegisterView = new StudentRegisterView(this, scanner);
        this.teacherLoginView = new TeacherLoginView(this, scanner);
        this.passphraseView = new PassphraseView(this, scanner);
    }

    @Override
    public void startView() {

        ArrayList<String> options = new ArrayList<>(Arrays.asList("Entrar como aluno", "Entrar como professor",
                "Criar nova conta", "Admin", "Sair"));

        String input = bakeMenu("MENU PRINCIPAL", options);

        if (input.equals("1")) {
            clearScreen();
            studentLoginView.startView();
        } else if (input.equals("2")) {
            clearScreen();
            teacherLoginView.startView();
        } else if (input.equals("3")) {
            clearScreen();
            studentRegisterView.startView();
        } else if (input.equals("4")) {
            clearScreen();
            passphraseView.startView();
        } else if (input.equals("5")) {
            clearScreen();
        }

        scanner.close();
    }

}
