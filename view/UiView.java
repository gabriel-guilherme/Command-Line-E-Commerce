package view;

import java.util.List;
import java.util.Scanner;

public class UiView implements View {
    protected Scanner scanner;

    public void startView() {
    };

    public String bakeMenu(String description, List<String> options) {
        // clearScreen();
        System.out.println(description + "\n");

        System.out.println(formatMenu(options));

        return scanner.nextLine();
    }

    public String bakeMenu(String description) {
        // clearScreen();
        System.out.println(description + "\n");

        return scanner.nextLine();

    }

    private String formatMenu(List<String> options) {
        StringBuilder formattedSubjects = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            formattedSubjects.append(i + 1).append(". ").append(options.get(i)).append("\n\n");
        }

        return formattedSubjects.toString();
    }

    protected void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
