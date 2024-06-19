package service;

import java.util.List;
import java.util.stream.Collectors;

import dao.ExamDao;
import dao.UserDao;
import entity.Exam;
import entity.User;
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

        public void updateExam(Exam exam) throws DAOException, ExamException {
                ExamDao examDao = new ExamDao();

                List<Exam> foundExams = examDao.findAll().stream()
                                .filter(currExam -> currExam.getName().equals(exam.getName())
                                                && currExam.getSubject().equals(exam.getSubject()))
                                .collect(Collectors.toList());

                Exam foundExam = foundExams.get(0);

                examDao.update(foundExam.getId(), foundExam);
        }

        public List<String> listStudents(Exam exam) throws DAOException, ExamException {
                ExamDao examDao = new ExamDao();

                List<Exam> foundExams = examDao.findAll().stream()
                                .filter(currExam -> currExam.getName().equals(exam.getName())
                                                && currExam.getSubject().equals(exam.getSubject()))
                                .collect(Collectors.toList());

                Exam foundExam = foundExams.get(0);

                return foundExam.getStudents();

        }

        public User getStudent(Exam exam, String studentLogin) throws DAOException, ExamException {
                UserDao userDao = new UserDao();

                List<User> students = userDao.findAll(user -> user.getExams().contains(exam));

                User student = students.stream()
                                .filter(s -> s.getLogin().equals(studentLogin))
                                .findFirst()
                                .orElseThrow(() -> new ExamException("Student with login " + studentLogin
                                                + " not found for the given exam."));

                return student;
        }
        /*
         * public void updateQuestion(Exam exam, Question question, int questionNumber)
         * {
         * try {
         * ExamDao examDao = new ExamDao();
         * 
         * Exam foundExam;
         * 
         * foundExam = examDao.findAll().stream()
         * .filter(currExam -> currExam.getName().equals(exam.getName())
         * && currExam.getSubject().equals(exam.getSubject()))
         * .collect(Collectors.toList())
         * .get(0);
         * 
         * // Question foundQuestion = foundExam.getQuestion(questionNumber);
         * List<Question> questions = foundExam.getQuestions();
         * 
         * questions.set(questionNumber, question);
         * 
         * foundExam.setQuestions(questions);
         * 
         * System.out.println(foundExam.getId() + "      " + foundExam);
         * examDao.update(foundExam.getId(), foundExam);
         * 
         * } catch (DAOException e) {
         * System.out.println(e.getMessage());
         * }
         * }
         */

}
