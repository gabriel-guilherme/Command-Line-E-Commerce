package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.TeacherDao;
import entity.Teacher;
import exception.DAOException;
import exception.TeacherException;

public class TeacherService {

    public void register(Teacher teacher, String subject) throws DAOException, TeacherException {
        if (subject.equals("")) {
            throw new TeacherException("Operação cancelada devido a espaços em branco");
        }
        TeacherDao teacherDao = new TeacherDao();

        List<Teacher> foundUsers = teacherDao.findAll().stream()
                .filter(user -> user.getLogin().equals(teacher.getLogin()))
                .collect(Collectors.toList());

        Teacher foundTeacher = foundUsers.get(0);

        if (foundTeacher.getSubjects() == null) {
            foundTeacher.setSubjects(new ArrayList<String>());
        }

        if (foundTeacher.getSubjects().contains(subject)) {
            throw new TeacherException("Matéria já registrada.");
        }

        foundTeacher.addSubject(subject);

        teacherDao.update(foundTeacher.getId(), foundTeacher);
    }

    public void listExams(Teacher teacher) {

    }

    /*
     * public void login(User teacher) throws DAOException {
     * UserDao userDao = new UserDao();
     * 
     * userDao.findAll((User user) -> {
     * return user.getLogin().equals(teacher.getLogin());
     * }).forEach(System.out::println);
     * System.out.print('a');
     * }
     */
}
