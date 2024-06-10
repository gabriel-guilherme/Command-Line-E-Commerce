package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entity.Exam;
import entity.Question;
import entity.Teacher;
import exception.DAOException;
import exception.ExamException;
import service.ExamService;

public class AddExamView implements View {
    private Teacher teacher;
    private Scanner scanner;
    private TeacherView teacherView;
    private ExamService examService = new ExamService();

    public AddExamView(Scanner scanner, TeacherView teacherView) {
        this.scanner = scanner;
        this.teacherView = teacherView;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public void startView() {

        if (teacher.getSubjects() == null || teacher.getSubjects().isEmpty()) {
            System.out.println("Cadastre ao menos uma matéria para adicionar provas.\n");
        } else {
            System.out.println("Insira o nome da prova:\n");
            String name = scanner.nextLine();

            clearScreen();

            System.out.println("Escolha a matéria referente a prova:\n");

            StringBuilder formattedSubjects = new StringBuilder();
            for (int i = 0; i < teacher.getSubjects().size(); i++) {
                formattedSubjects.append(i + 1).append(". ").append(teacher.getSubjects().get(i)).append("\n");
            }

            System.out.print(formattedSubjects.toString() + "\n");

            List<Question> questions = new ArrayList<Question>();

            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                String subject = teacher.getSubjects().get(input - 1);

                clearScreen();

                if (name.isEmpty() || subject.isEmpty()) {
                    System.out.println("Operação cancelada devido a espaços em branco.\n");
                    teacherView.startView();
                    return;
                }

                System.out.println("1. Fazer questões pelo console\n\n2. Fazer questões por arquivo\n");
                input = scanner.nextInt();
                scanner.nextLine();

                if (input == 1) {
                    clearScreen();
                    System.out.println("Informe quantas questões terá a prova:\n");
                    input = scanner.nextInt();
                    scanner.nextLine();

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
                            System.out.println("Operação cancelada devido a espaços em branco.\n");
                            teacherView.startView();
                            return;
                        }

                    }
                } else if (input == 2) {

                }

                examService.addExam(new Exam(name, subject, questions));
                System.out.println("Prova cadastrada.\n");

            } catch (InputMismatchException e) {
                clearScreen();
                System.out.println("Erro: Por favor, insira um número válido.\n");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                clearScreen();
                System.out.println("Erro: O número escolhido não corresponde a nenhuma matéria disponível.\n");
            } catch (DAOException | ExamException e) {
                System.out.println(e.getMessage() + "\n");
            }
        }

        teacherView.startView();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}