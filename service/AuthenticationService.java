package service;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import dao.AdminDao;
import dao.StudentDao;
import dao.TeacherDao;
import entity.Admin;
import entity.Student;
import entity.Teacher;
import exception.DAOException;

public class AuthenticationService {

    public void register(Student student) throws DAOException, AuthenticationException {
        StudentDao studentDao = new StudentDao();

        List<Student> foundUsers = studentDao.findAll().stream()
                .filter(user -> user.getLogin().equals(student.getLogin()))
                .collect(Collectors.toList());

        if (!foundUsers.isEmpty()) {
            throw new AuthenticationException("Matrícula já registrada.");
        }

        studentDao.save(student);
    }

    public void register(Teacher teacher) throws DAOException, AuthenticationException {
        TeacherDao teacherDao = new TeacherDao();

        List<Teacher> foundUsers = teacherDao.findAll().stream()
                .filter(user -> user.getLogin().equals(teacher.getLogin()))
                .collect(Collectors.toList());

        if (!foundUsers.isEmpty()) {
            throw new AuthenticationException("Matrícula já registrada.");
        }

        teacherDao.save(teacher);
    }

    public Student login(Student student) throws DAOException, AuthenticationException {
        StudentDao studentDao = new StudentDao();

        List<Student> findedUser = studentDao.findAll((Student user) -> {
            return user.getLogin().equals(student.getLogin());
        });

        if (findedUser.isEmpty() || !findedUser.get(0).getPassword().equals(student.getPassword())) {
            throw new AuthenticationException("Matrícula não encontrada ou senha incorreta.");
        }

        return findedUser.get(0);
    }

    public Teacher login(Teacher teacher) throws DAOException, AuthenticationException {
        TeacherDao teacherDao = new TeacherDao();

        List<Teacher> findedUser = teacherDao.findAll((Teacher user) -> {
            return user.getLogin().equals(teacher.getLogin());
        });

        if (findedUser.isEmpty() || !findedUser.get(0).getPassword().equals(teacher.getPassword())) {
            throw new AuthenticationException("Nome de usuário não encontrado ou senha incorreta.");
        }

        return findedUser.get(0);
    }

    public void admin(String password) throws DAOException, AuthenticationException {
        AdminDao adminDao = new AdminDao();

        Admin admin = adminDao.findById(1).get();

        if (!admin.getPassword().equals(password)) {
            throw new AuthenticationException("Palavra chave incorreta.");
        }
        return;
    }

    public void updateAdmin(String password) throws DAOException {
        AdminDao adminDao = new AdminDao();

        Admin admin = adminDao.findById(1).get();

        admin.setPassword(password);

        adminDao.update(1, admin);
    }
}
