package service;

//import java.io.File;

import java.util.List;
import java.util.stream.Collectors;

import dao.ExamDao;
import dao.UserDao;
import entity.Exam;
import entity.Question;
//import entity.Question;
import entity.User;
import exception.DAOException;
import exception.ExamException;

public class ExamService {

        public void addExam(Exam exam) throws ExamException {
                try {
                        ExamDao examDao = new ExamDao();

                        List<Exam> foundExams = examDao.findAll().stream()
                                        .filter(currExam -> currExam.getName().equals(exam.getName())
                                                        && currExam.getSubject().equals(exam.getSubject()))
                                        .collect(Collectors.toList());

                        if (!foundExams.isEmpty()) {
                                throw new ExamException("ERRO - Prova com mesmo nome encontrada.\n");
                        }

                        examDao.save(exam);
                } catch (DAOException e) {
                        throw new ExamException(e.getMessage(), e);
                }
        }

        public void updateExam(Exam exam) throws ExamException {
                try {
                        ExamDao examDao = new ExamDao();

                        List<Exam> foundExams = examDao.findAll().stream()
                                        .filter(currExam -> currExam.getName().equals(exam.getName())
                                                        && currExam.getSubject().equals(exam.getSubject()))
                                        .collect(Collectors.toList());

                        Exam foundExam = foundExams.get(0);

                        examDao.update(foundExam.getId(), foundExam);
                } catch (DAOException e) {
                        throw new ExamException(e.getMessage(), e);
                }
        }

        public List<String> listStudents(Exam exam) throws ExamException {
                try {
                        ExamDao examDao = new ExamDao();

                        List<Exam> foundExams = examDao.findAll().stream()
                                        .filter(currExam -> currExam.getName().equals(exam.getName())
                                                        && currExam.getSubject().equals(exam.getSubject()))
                                        .collect(Collectors.toList());

                        Exam foundExam = foundExams.get(0);

                        return foundExam.getStudents();
                } catch (DAOException e) {
                        throw new ExamException(e.getMessage(), e);
                }

        }

        public User getStudent(Exam exam, String studentLogin) throws ExamException {
                try {
                        UserDao userDao = new UserDao();

                        List<User> students = userDao.findAll(user -> user.getExams().contains(exam));

                        User student = students.stream()
                                        .filter(s -> s.getLogin().equals(studentLogin))
                                        .findFirst()
                                        .orElseThrow(() -> new ExamException("Student with login " + studentLogin
                                                        + " not found for the given exam."));

                        return student;
                } catch (DAOException e) {
                        throw new ExamException(e.getMessage(), e);
                }
        }

        public void correctAlternativeQuestions(Exam exam) throws ExamException {
                List<Question> questions = exam.getQuestions();

                questions.forEach((question) -> {
                        if (question.getType() && question.getCorrectAlternative().equals(question.getResponse())) {
                                question.setGrade(question.getValue());
                        }
                });
        }
}
