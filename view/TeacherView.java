package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import entity.Teacher;
import exception.DAOException;
import exception.TeacherException;
import service.TeacherService;

public class TeacherView extends UiView {
    private Teacher teacher;
    private MainView mainView;
    private TeacherService teacherService = new TeacherService();
    private AddExamView addExamView;

    public TeacherView(Scanner scanner, MainView mainView, Teacher teacher) {
        this.scanner = scanner;
        this.mainView = mainView;
        this.teacher = teacher;
        this.addExamView = new AddExamView(scanner, this, teacher);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public void startView() {

        ArrayList<String> options = new ArrayList<>(
                Arrays.asList("Adicionar prova", "Corrigir provas", "Registrar nova matéria", "Voltar"));

        String input = bakeMenu("BEM VINDO " + teacher.getName() + " MENU DO PROFESSOR", options);

        if (input.equals("1")) {
            clearScreen();

            addExamView.startView();
        } else if (input.equals("2")) {

        } else if (input.equals("3")) {
            clearScreen();

            input = bakeMenu("Informe a matéria que você quer registrar:");

            try {
                teacherService.register(teacher, input);
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