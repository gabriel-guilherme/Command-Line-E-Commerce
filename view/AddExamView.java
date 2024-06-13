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

    public AddExamView(Scanner scanner, TeacherView teacherView, User user) {
        this.scanner = scanner;
        this.teacherView = teacherView;
        this.user = user;
    }

    public void setTeacher(User user) {
        this.user = user;
    }

    public User getTeacher() {
        return user;
    }

    @Override
    public void startView() {

        if (user.getSubjects() == null || user.getSubjects().isEmpty()) {
            System.out.println("Cadastre ao menos uma matéria para adicionar provas.\n");
        } else {
            String name = bakeMenu("Insira o nome da prova:");

            clearScreen();

            try {
                int input = Integer.parseInt(bakeMenu("Escolha a matéria referente a prova:", user.getSubjects()));

                String subject = user.getSubjects().get(input - 1);

                clearScreen();

                if (name.isEmpty() || subject.isEmpty()) {
                    System.out.println("Operação cancelada devido a espaços em branco.\n");
                    teacherView.startView();
                    return;
                }

                ArrayList<String> options = new ArrayList<>(
                        Arrays.asList("Fazer questões pelo console", "Fazer questões por arquivo"));

                input = Integer.parseInt(bakeMenu("", options));

                if (input == 1) {
                    clearScreen();
                    addQuestionView = new AddQuestionView(scanner, teacherView, name, subject);
                    addQuestionView.startView();

                } else if (input == 2) {

                }

            } catch (InputMismatchException | NumberFormatException e) {
                clearScreen();
                System.out.println("Erro: Por favor, insira um número válido.\n");
            } catch (IndexOutOfBoundsException e) {
                clearScreen();
                System.out.println("Erro: O número escolhido não corresponde a nenhuma matéria disponível.\n");
            }
        }

        teacherView.startView();
    }
}