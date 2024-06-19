package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Exam;
import entity.Question;
import exception.ExamException;
import service.ExamService;

public class FileQuestionView extends UiView {

    // private Scanner scanner;
    // private TeacherView teacherView;
    private ExamService examService = new ExamService();

    private String name;
    private String subject;

    public FileQuestionView(Scanner scanner, TeacherView teacherView) {
        this.scanner = scanner;
        // this.teacherView = teacherView;
    }

    public void startView() {
        try {
            List<Question> questions = new ArrayList<Question>();

            String input = bakeMenu("Informe o arquivo (deve estar localizado na pasta exams)");

            // questions = examService.fileQuestionCreater();

            System.out.println(input);

            examService.addExam(new Exam(name, subject, questions));
        } catch (ExamException e) {
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
