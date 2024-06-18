package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dao.ExamDao;
import dao.UserDao;
import entity.Exam;
import entity.Question;
import entity.User;
import exception.DAOException;
import exception.ExamException;
import exception.StudentException;

public class StudentService {

    public void register(User student, String subject) throws DAOException, StudentException {
        UserDao userDao = new UserDao();

        List<User> foundUsers = userDao.findAll().stream()
                .filter(user -> user.getLogin().equals(student.getLogin()))
                .collect(Collectors.toList());

        User foundStudent = foundUsers.get(0);

        if (foundStudent.getSubjects() == null) {
            foundStudent.setSubjects(new ArrayList<String>());
        }

        if (foundStudent.getSubjects().contains(subject)) {
            throw new StudentException("Matéria já registrada.");
        }

        foundStudent.addSubject(subject);

        userDao.update(foundStudent.getId(), foundStudent);
    }

    public ArrayList<String> listExams(User student) throws DAOException, StudentException {
        if (student.getSubjects() == null || student.getSubjects().isEmpty()) {
            throw new StudentException("É necessário pelo menos uma matéria cadastrada.");
        }

        ExamDao examDao = new ExamDao();

        List<String> exams = examDao.findAll().stream()
                .filter(exam -> student.getSubjects().contains(exam.getSubject()))
                .map(Exam::toString)
                .collect(Collectors.toList());

        ArrayList<String> studentExams = student.getExams().stream()
                .filter(exam -> exams.contains(exam.toString()))
                .map(Exam::toString)
                .collect(Collectors.toCollection(ArrayList::new));

        Set<String> combinedExamsSet = new HashSet<>(studentExams);
        combinedExamsSet.addAll(exams);

        ArrayList<String> combinedExams = new ArrayList<>(combinedExamsSet);

        return combinedExams;
    }

    public List<Exam> getExams(User student) throws DAOException, ExamException {
        ExamDao examDao = new ExamDao();

        List<Exam> exams = examDao.findAll().stream().collect(Collectors.toList());

        /*
         * if (exams.isEmpty()) {
         * throw new
         * ExamException("Não há provas disponíveis, verifique as matérias cadastradas."
         * );
         * }
         */
        return exams;
    }

    public void updateQuestion(Exam exam, Question question, User student) throws DAOException, StudentException {
        try {
            UserDao userDao = new UserDao();

            List<User> foundUsers = userDao.findAll().stream()
                    .filter(user -> user.getLogin().equals(student.getLogin()))
                    .collect(Collectors.toList());

            User foundUser = foundUsers.get(0);

            if (foundUser.getExam(exam) == null) {
                foundUser.addExam(exam);

                userDao.update(foundUser.getId(), foundUser);
                return;
            }

            List<Question> userQuestions = foundUser.getExam(exam).getQuestions();

            userQuestions.set(question.getId(), question);

            foundUser.getExam(exam).setQuestions(userQuestions);

        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveExam(Exam exam, User student) throws DAOException {
        UserDao userDao = new UserDao();

        List<User> foundUsers = userDao.findAll().stream()
                .filter(user -> user.getLogin().equals(student.getLogin()))
                .collect(Collectors.toList());

        User foundStudent = foundUsers.get(0);

        foundStudent.addExam(exam);
    }

    public boolean checkExam(Exam exam, User student) throws DAOException {
        UserDao userDao = new UserDao();

        List<User> foundUsers = userDao.findAll().stream()
                .filter(user -> user.getLogin().equals(student.getLogin()))
                .collect(Collectors.toList());

        User foundStudent = foundUsers.get(0);

        boolean examExists = foundStudent.getExams().stream()
                .anyMatch(e -> e.equals(exam));

        return examExists;
    }
}
