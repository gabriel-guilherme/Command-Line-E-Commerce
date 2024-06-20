package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.User;

public class AddExamView extends UiView {
    private User user;
    private TeacherView teacherView;
    private AddQuestionView addQuestionView;
    // private FileQuestionView fileQuestionView;

    public AddExamView(Scanner scanner, TeacherView teacherView, User user) {
        this.scanner = scanner;
        this.teacherView = teacherView;
        this.user = user;
    }

    @Override
    public void startView() {

        if (user.getSubjects() == null || user.getSubjects().isEmpty()) {
            System.out.println("Cadastre ao menos uma matéria para adicionar provas.\n");
            teacherView.startView();
        } else {
            try {
                ArrayList<String> options = new ArrayList<>(
                        Arrays.asList("Fazer questões pelo console", "Fazer questões por arquivo"));
                int input = Integer.parseInt(bakeMenu("", options));

                if (input == 1) {
                    clearScreen();
                    String name = bakeMenu("Insira o nome da prova:");

                    clearScreen();

                    input = Integer.parseInt(bakeMenu("Escolha a matéria referente a prova:", user.getSubjects()));

                    String subject = user.getSubjects().get(input - 1);

                    clearScreen();

                    if (name.isEmpty() || subject.isEmpty()) {
                        System.out.println("Operação cancelada devido a espaços em branco.\n");
                        teacherView.startView();
                        return;
                    }

                    clearScreen();
                    addQuestionView = new AddQuestionView(scanner, teacherView, name, subject);
                    addQuestionView.startView();

                } else if (input == 2) {
                    /*
                     * clearScreen();
                     * fileQuestionView = new FileQuestionView(scanner, teacherView);
                     * fileQuestionView.startView();
                     */
                } else {
                    throw new IndexOutOfBoundsException();
                }
            } catch (InputMismatchException | NumberFormatException e) {
                clearScreen();
                System.out.println("Erro: Por favor, insira um número válido.\n");
            } catch (IndexOutOfBoundsException e) {
                clearScreen();
                System.out.println("\"Erro: Por favor, insira um número válido.\n");

                this.startView();
            }

            teacherView.startView();
        }
    }
}