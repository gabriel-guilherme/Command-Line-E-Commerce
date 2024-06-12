package view;

import java.util.Scanner;

import entity.Exam;

public class ExamView implements View {
    private Scanner scanner;
    private StudentView studentView;
    private Exam exam;

    public ExamView(Scanner scanner, StudentView studentView, Exam exam) {
        this.scanner = scanner;
        this.studentView = studentView;
        this.exam = exam;
    }

    @Override
    public void startView() {
        // System.out.print("\033[H\033[2J");
        // System.out.flush();

        System.out.println("");

        String input = scanner.nextLine();

        scanner.close();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
