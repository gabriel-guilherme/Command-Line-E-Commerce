package service;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import dao.AdminDao;
import dao.UserDao;
import entity.Admin;
import entity.User;
import exception.DAOException;

public class AuthenticationService {

    public void register(User user) throws DAOException, AuthenticationException {
        if (user.getLogin().equals("") || user.getName().equals("") || user.getPassword().equals("")) {
            throw new AuthenticationException("Operação cancelada devido a espaços em branco.");
        }
        UserDao userDao = new UserDao();

        List<User> foundUsers = userDao.findAll().stream()
                .filter(currUser -> currUser.getLogin().equals(user.getLogin()))
                .collect(Collectors.toList());

        if (!foundUsers.isEmpty()) {
            throw new AuthenticationException("Usuário já registrado.");
        }

        userDao.save(user);
    }

    public User login(User user) throws DAOException, AuthenticationException {
        UserDao userDao = new UserDao();

        List<User> findedUser = userDao.findAll((User currUser) -> {
            return currUser.getLogin().equals(user.getLogin());
        });

        if (findedUser.isEmpty() || !findedUser.get(0).getPassword().equals(user.getPassword())) {
            throw new AuthenticationException("Usuário não encontrado ou senha incorreta.");
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

    public void updateAdmin(String password) throws DAOException, AuthenticationException {
        if (password == "") {
            throw new AuthenticationException("Operação cancelada devido a espaços em branco.");
        }

        AdminDao adminDao = new AdminDao();

        Admin admin = adminDao.findById(1).get();

        admin.setPassword(password);

        adminDao.update(1, admin);
    }
}
