package view;

import java.util.Scanner;

public class AdminView implements View {
    private Scanner scanner;
    private MainView mainView;
    private TeacherRegisterView teacherRegisterView;
    private UpdateAdminView updateAdminView;
    // private StudentService studentService = new StudentService();

    AdminView(MainView mainView, Scanner scanner) {
        this.scanner = scanner;
        this.mainView = mainView;
        this.updateAdminView = new UpdateAdminView(this, scanner);
        this.teacherRegisterView = new TeacherRegisterView(this, scanner);
    }

    @Override
    public void startView() {
        // System.out.print("\033[H\033[2J");
        // System.out.flush();

        System.out.println("ADMIN MENU\n\n1. Registrar professor\n\n2. Mudar palavra-chave.\n\n3. Voltar\n");

        String input = scanner.nextLine();

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

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
