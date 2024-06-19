package view;

import java.util.List;
import java.util.Scanner;

import entity.Exam;
import entity.User;
import exception.ExamException;
import service.ExamService;

public class CorrectView extends UiView {
    private ExamService examService = new ExamService();
    private Exam exam;
    private ExamView examView;
    private TeacherView teacherView;

    public CorrectView(Scanner scanner, Exam exam, TeacherView teacherView) {
        this.scanner = scanner;
        this.exam = exam;
        this.teacherView = teacherView;
    }

    @Override
    public void startView() {
        try {
            List<String> options = examService.listStudents(exam);

            int input = Integer.parseInt(bakeMenu("Alunos que finalizaram:", options));

            User student = examService.getStudent(exam, options.get(input - 1));

            examView = new ExamView(scanner, this, exam, student);
            examView.startView();
            /*
             * List<Exam> exams = teacherService.listExams(teacher);
             * List<String> options = exams.stream()
             * .map(exam -> exam.toString())
             * .collect(Collectors.toList());
             * 
             * String input = bakeMenu("Provas disponíveis:", options);
             */

            // correctView.startView();

        } catch (ExamException e) {
            clearScreen();

            System.out.println(e.getMessage());
            teacherView.startView();
        } catch (NumberFormatException e) {
            clearScreen();
            System.out.println("\"Erro: Por favor, insira um número válido.\n");
            teacherView.startView();
        }
    }

}