import java.util.Scanner;

import dao.AdminDao;
import dao.ExamDao;
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

        /*
         * ExamDao examDao = new ExamDao();
         * 
         * try {
         * System.out.println(examDao.findAll());
         * } catch (DAOException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         */
    }
}
