package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Name1", "Surname1", (byte) 10);
        userDao.saveUser("Name2", "Surname2", (byte) 20);
        userDao.saveUser("Name3", "Surname3", (byte) 30);
        userDao.saveUser("Name4", "Surname4", (byte) 40);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

//        UserService userService = new UserServiceImpl();
//        userService.createUsersTable();
//
//        userService.saveUser("Name01", "Surname01", (byte) 10);
//        userService.saveUser("Name02", "Surname02", (byte) 20);
//        userService.saveUser("Name03", "Surname03", (byte) 25);
//        userService.saveUser("Name04", "Surname04", (byte) 30);
//
//        List<User> allUsers = userService.getAllUsers();
//        printUsers(allUsers);
//        userService.removeUserById(1);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
//    }
//
//    private static void printUsers(List<User> users) {
//        for (User user : users) {
//            System.out.println(user);
//        }


    }
}
