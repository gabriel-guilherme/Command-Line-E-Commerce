package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.StudentDao;
import entity.Student;
import exception.DAOException;
import exception.StudentException;

public class StudentService {

    public void register(Student student, String subject) throws DAOException, StudentException {
        StudentDao studentDao = new StudentDao();

        List<Student> foundUsers = studentDao.findAll().stream()
                .filter(user -> user.getLogin().equals(student.getLogin()))
                .collect(Collectors.toList());

        Student foundStudent = foundUsers.get(0);

        if (foundStudent.getSubjects() == null) {
            foundStudent.setSubjects(new ArrayList<String>());
        }

        if (foundStudent.getSubjects().contains(subject)) {
            throw new StudentException("Matéria já registrada.");
        }

        foundStudent.addSubject(subject);

        studentDao.update(foundStudent.getId(), foundStudent);
    }

    public void listExams(Student student) {

    }

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
