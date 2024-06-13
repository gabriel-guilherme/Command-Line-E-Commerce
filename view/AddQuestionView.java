package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Exam;
import entity.Question;
import exception.DAOException;
import exception.ExamException;
import service.ExamService;

public class AddQuestionView extends UiView {

    // private Scanner scanner;
    private TeacherView teacherView;
    private ExamService examService = new ExamService();

    private String name;
    private String subject;

    public AddQuestionView(Scanner scanner, TeacherView teacherView, String name, String subject) {
        this.scanner = scanner;
        this.teacherView = teacherView;
        this.name = name;
        this.subject = subject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public void startView() {
        try {
            List<Question> questions = new ArrayList<Question>();

            int input = Integer.parseInt(bakeMenu("Informe quantas questões terá a prova"));

            System.out.println(input);

            if (input <= 0) {
                clearScreen();
                System.out.println("Erro: Por favor, insira um número válido.\n");
                teacherView.startView();
                return;
            }

            //////////////// MUDAR///////////////////////
            for (int i = 0; i < input; i++) {
                clearScreen();
                System.out.println("Informe o tipo da questão " + (i + 1) + ":\n");
                Boolean isObjective = scanner.nextBoolean();
                scanner.nextLine();

                clearScreen();
                System.out.println("Informe o enunciado da questão " + (i + 1) + ":\n");
                String description = scanner.nextLine();

                clearScreen();
                System.out.println("Informe o valor da questão " + (i + 1) + ":\n");
                Float value = scanner.nextFloat();
                scanner.nextLine();

                Question question = new Question(description, isObjective, value);

                if (isObjective) {
                    clearScreen();
                    System.out.println("Informe as 4 alternativas da questão " + (i + 1) + ":\n");
                    List<String> alternatives = new ArrayList<>();

                    System.out.println("\nInforme a alternativa 1:\n");
                    alternatives.add(scanner.nextLine());
                    System.out.println("\nInforme a alternativa 2:\n");
                    alternatives.add(scanner.nextLine());
                    System.out.println("\nInforme a alternativa 3:\n");
                    alternatives.add(scanner.nextLine());
                    System.out.println("\nInforme a alternativa 4:\n");
                    alternatives.add(scanner.nextLine());

                    question.setAlternatives(alternatives);
                }

                if (description.equals("") || value == null) {
                    clearScreen();
                    System.out.println("Operação cancelada devido a espaços em branco.\n");
                    teacherView.startView();
                    return;
                }

            }

            examService.addExam(new Exam(name, subject, questions));
        } catch (DAOException | ExamException e) {
            clearScreen();
            System.out.println(e.getMessage());
            return;
        } catch (NumberFormatException e) {
            clearScreen();
            System.out.println("\"Erro: Por favor, insira um número válido.\n");
            return;
        }

        clearScreen();
        System.out.println("Prova cadastrada.\n");

    }

}
