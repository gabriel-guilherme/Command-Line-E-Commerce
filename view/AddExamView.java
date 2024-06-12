package view;

import java.util.InputMismatchException;

import java.util.Scanner;

import entity.Teacher;

public class AddExamView implements View {
    private Teacher teacher;
    private Scanner scanner;
    private TeacherView teacherView;
    private AddQuestionView addQuestionView;

    public AddExamView(Scanner scanner, TeacherView teacherView, Teacher teacher) {
        this.scanner = scanner;
        this.teacherView = teacherView;
        this.teacher = teacher;
        // this.addQuestionView = new AddQuestionView(scanner, teacherView);
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

                // addQuestionView.setName(name);
                // addQuestionView.setSubject(subject);
                addQuestionView = new AddQuestionView(scanner, teacherView, name, subject);
                addQuestionView.startView();

            } catch (InputMismatchException e) {
                clearScreen();
                System.out.println("Erro: Por favor, insira um número válido.\n");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                clearScreen();
                System.out.println("Erro: O número escolhido não corresponde a nenhuma matéria disponível.\n");
            }
        }

        teacherView.startView();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}