package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import entity.User;
import exception.DAOException;
import exception.TeacherException;
import service.TeacherService;

public class TeacherView extends UiView {
    private User user;
    private MainView mainView;
    private TeacherService teacherService = new TeacherService();
    private AddExamView addExamView;

    public TeacherView(Scanner scanner, MainView mainView, User user) {
        this.scanner = scanner;
        this.mainView = mainView;
        this.user = user;
        this.addExamView = new AddExamView(scanner, this, user);
    }

    public void setTeacher(User user) {
        this.user = user;
    }

    public User getTeacher() {
        return user;
    }

    @Override
    public void startView() {

        ArrayList<String> options = new ArrayList<>(
                Arrays.asList("Adicionar prova", "Corrigir provas", "Registrar nova matéria", "Voltar"));

        String input = bakeMenu("BEM VINDO " + user.getName() + " MENU DO PROFESSOR", options);

        if (input.equals("1")) {
            clearScreen();

            addExamView.startView();
        } else if (input.equals("2")) {

        } else if (input.equals("3")) {
            clearScreen();

            input = bakeMenu("Informe a matéria que você quer registrar:");

            try {
                teacherService.register(user, input);
                clearScreen();
            } catch (DAOException | TeacherException e) {
                clearScreen();
                System.out.println(e.getMessage() + "\n");
            }
            this.startView();
        } else if (input.equals("4")) {
            clearScreen();
            mainView.startView();
        }
    }

}