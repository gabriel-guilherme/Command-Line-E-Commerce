package view;

import java.util.Scanner;

public class MainView implements View {
    private Scanner scanner;
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
        // System.out.print("\033[H\033[2J");
        // System.out.flush();

        System.out.println(
                "MENU PRINCIPAL\n\n1. Entrar como aluno\n\n2. Entrar como professor\n\n3. Criar nova conta\n\n4. Admin\n\n5. Sair\n");

        String input = scanner.nextLine();

        if (input.equals("1")) {
            studentLoginView.startView();
        } else if (input.equals("2")) {
            teacherLoginView.startView();
        } else if (input.equals("3")) {
            studentRegisterView.startView();
        } else if (input.equals("4")) {
            passphraseView.startView();
        } else if (input.equals("5")) {
            clearScreen();
        }

        scanner.close();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
