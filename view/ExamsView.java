package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entity.Exam;
import entity.User;
import exception.TeacherException;
import service.TeacherService;

public class ExamsView extends UiView {
    private User teacher;
    private TeacherView teacherView;
    private TeacherService teacherService = new TeacherService();
    private CorrectView correctView;

    public ExamsView(Scanner scanner, TeacherView teacherView, User teacher) {
        this.scanner = scanner;
        this.teacherView = teacherView;
        this.teacher = teacher;

    }

    @Override
    public void startView() {
        try {
            List<Exam> exams = teacherService.listExams(teacher);
            List<String> options = exams.stream()
                    .map(exam -> exam.toString())
                    .collect(Collectors.toList());

            int input = Integer.parseInt(bakeMenu("Provas disponíveis:", options));

            correctView = new CorrectView(scanner, exams.get(input - 1), teacherView);

            clearScreen();
            correctView.startView();
        } catch (TeacherException e) {
            clearScreen();

            System.out.println(e.getMessage());
            this.startView();
        } catch (NumberFormatException e) {
            clearScreen();
            System.out.println("\"Erro: Por favor, insira um número válido.\n");
            this.startView();
        }
    }

}