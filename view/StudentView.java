package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import dao.ExamDao;
import entity.Exam;
import entity.Student;
import exception.DAOException;
import exception.ExamException;
import exception.StudentException;
import service.StudentService;

public class StudentView implements View {
    private Student student;
    private Scanner scanner;
    private MainView mainView;
    private ExamView examView;
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

            ExamDao examDao = new ExamDao();

            try {
                List<Exam> exams = examDao.findAll().stream().collect(Collectors.toList());

                clearScreen();

                System.out.println("Escolha a prova:\n");

                StringBuilder formattedSubjects = new StringBuilder();
                for (int i = 0; i < exams.size(); i++) {
                    formattedSubjects.append(i + 1).append(". ").append(exams.get(i)).append("\n");
                }

                System.out.print(formattedSubjects.toString() + "\n");

                examView = new ExamView(scanner, this, exams.get(scanner.nextInt()));
                scanner.nextLine();
                // int chosedExam = scanner.nextInt();

                examView.startView();
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            /*
             * if (student.getSubjects() == null || student.getSubjects().isEmpty()) {
             * System.out.println("Cadastre ao menos uma matéria para adicionar provas.\n");
             * } else {
             * 
             * }
             */
        } else if (input.equals("2")) {

            clearScreen();

            System.out.println("Informe a matéria que você quer registrar:\n");

            input = scanner.nextLine();
            try {
                studentService.register(student, input);
                clearScreen();
            } catch (DAOException | StudentException e) {
                clearScreen();

                System.out.println(e.getMessage() + "\n");
            }

            this.startView();
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
