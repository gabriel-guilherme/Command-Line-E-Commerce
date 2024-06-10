package view;

import java.util.Scanner;

import entity.Student;
import exception.DAOException;
import exception.StudentException;
import service.StudentService;

public class StudentView implements View {
    private Student student;
    private Scanner scanner;
    private MainView mainView;
    private StudentService studentService = new StudentService();

    StudentView(Scanner scanner, MainView mainView) {
        this.scanner = scanner;
        this.mainView = mainView;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public void startView() {
        // System.out.print("\033[H\033[2J");
        // System.out.flush();

        System.out.println(
                "BEM VINDO " + student.getName()
                        + " MENU DO ALUNO\n\n1. Provas\n\n2. Registrar nova matéria\n\n3. Voltar\n");

        String input = scanner.nextLine();

        if (input.equals("1")) {
            studentService.listExams(student);
            // System.out.println("Selecione sua matéria pelo número indicado\n");

            // studentService.list();
        } else if (input.equals("2")) {

            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Informe a matéria que você quer registrar:\n");

            input = scanner.nextLine();
            try {
                studentService.register(student, input);
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } catch (DAOException e) {
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println(e.getMessage() + "\n");
            } catch (StudentException e) {
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println(e.getMessage() + "\n");
            }

            this.startView();
        } else if (input.equals("3")) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            mainView.startView();
        }

        scanner.close();
    }

}
