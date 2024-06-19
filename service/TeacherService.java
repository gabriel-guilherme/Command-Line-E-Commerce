package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.ExamDao;
import dao.UserDao;
import entity.Exam;
import entity.User;
import exception.DAOException;
import exception.StudentException;
import exception.TeacherException;

public class TeacherService {

    public void register(User user, String subject) throws DAOException, TeacherException {
        if (subject.equals("")) {
            throw new TeacherException("Operação cancelada devido a espaços em branco");
        }
        UserDao userDao = new UserDao();

        List<User> foundUsers = userDao.findAll().stream()
                .filter(currUser -> currUser.getLogin().equals(user.getLogin()))
                .collect(Collectors.toList());

        User foundTeacher = foundUsers.get(0);

        if (foundTeacher.getSubjects() == null) {
            foundTeacher.setSubjects(new ArrayList<String>());
        }

        if (foundTeacher.getSubjects().contains(subject)) {
            throw new TeacherException("Matéria já registrada.");
        }

        foundTeacher.addSubject(subject);

        userDao.update(foundTeacher.getId(), foundTeacher);
    }

    public List<Exam> listExams(User teacher) throws DAOException, StudentException {
        if (teacher.getSubjects() == null || teacher.getSubjects().isEmpty()) {
            throw new StudentException("É necessário pelo menos uma matéria cadastrada.");
        }

        ExamDao examDao = new ExamDao();

        List<Exam> exams = examDao.findAll().stream()
                .filter(exam -> teacher.getSubjects().contains(exam.getSubject()))
                .collect(Collectors.toList());

        return exams;
    }

    // public void listExams(User user) {

    // }

    /*
     * public void login(User user) throws DAOException {
     * UserDao userDao = new UserDao();
     * 
     * userDao.findAll((User user) -> {
     * return user.getLogin().equals(user.getLogin());
     * }).forEach(System.out::println);
     * System.out.print('a');
     * }
     */
}
