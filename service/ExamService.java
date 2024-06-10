package service;

import java.util.List;
import java.util.stream.Collectors;

import dao.ExamDao;
import entity.Exam;
import exception.DAOException;
import exception.ExamException;

public class ExamService {

    public void addExam(Exam exam) throws DAOException, ExamException {
        ExamDao examDao = new ExamDao();

        List<Exam> foundExams = examDao.findAll().stream()
                .filter(currExam -> currExam.getName().equals(exam.getName())
                        && currExam.getSubject().equals(exam.getSubject()))
                .collect(Collectors.toList());

        if (!foundExams.isEmpty()) {
            throw new ExamException("ERRO - Prova com mesmo nome encontrada.");
        }

        examDao.save(exam);
    }
}
