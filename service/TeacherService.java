package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.ExamDao;
import dao.UserDao;
import entity.Exam;
import entity.User;
import exception.DAOException;
import exception.TeacherException;

public class TeacherService {

    public void register(User user, String subject) throws TeacherException {
        try {
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
        } catch (DAOException e) {
            throw new TeacherException(e.getMessage(), e);
        }
    }

    public List<Exam> listExams(User teacher) throws TeacherException {
        try {
            if (teacher.getSubjects() == null || teacher.getSubjects().isEmpty()) {
                throw new TeacherException("É necessário pelo menos uma matéria cadastrada.");
            }

            ExamDao examDao = new ExamDao();

            List<Exam> exams = examDao.findAll().stream()
                    .filter(exam -> teacher.getSubjects().contains(exam.getSubject()))
                    .collect(Collectors.toList());

            return exams;

        } catch (DAOException e) {
            throw new TeacherException(e.getMessage(), e);
        }
    }

}
