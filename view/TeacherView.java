package view;

import java.util.Scanner;

import entity.Teacher;
import exception.DAOException;
import exception.TeacherException;
import service.TeacherService;

public class TeacherView implements View {
    private Teacher teacher;
    private Scanner scanner;
    private MainView mainView;
    private TeacherService teacherService = new TeacherService();
    private AddExamView addExamView;

    public TeacherView(Scanner scanner, MainView mainView) {
        this.scanner = scanner;
        this.mainView = mainView;
        this.addExamView = new AddExamView(scanner, this);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public void startView() {

        System.out.println(
                "BEM VINDO " + teacher.getName()
                        + " MENU DO PROFESSOR\n\n1. Adicionar prova\n\n2. Corrigir provas\n\n3. Registrar nova matéria\n\n4. Voltar\n");

        String input = scanner.nextLine();

        if (input.equals("1")) {
            clearScreen();

            addExamView.setTeacher(teacher);
            addExamView.startView();
        } else if (input.equals("2")) {

        } else if (input.equals("3")) {
            clearScreen();
            System.out.println("Informe a matéria que você quer registrar:\n");
            input = scanner.nextLine();
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

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}