package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.UserDao;
import entity.User;
import exception.DAOException;
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

    // public void listExams(Student student) {

    // }

    /*
     * public void login(User student) throws DAOException {
     * UserDao userDao = new UserDao();
     * 
     * userDao.findAll((User user) -> {
     * return user.getLogin().equals(student.getLogin());
     * }).forEach(System.out::println);
     * System.out.print('a');
     * }
     */
}
