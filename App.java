import java.util.Scanner;

import dao.AdminDao;
import entity.Admin;
import exception.DAOException;
import exception.DatabaseException;
import view.MainView;

public class App {
    public static void main(String[] args) throws DatabaseException {
        AdminDao adminDao = new AdminDao();
        try {
            adminDao.save(new Admin("123"));
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(scanner);

        System.out.print("\033[H\033[2J");
        System.out.flush();
        mainView.startView();
    }
}
