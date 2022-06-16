package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Morgan", "Freeman", (byte) 85);
        userDao.saveUser("Clint", "Eastwood", (byte) 92);
        userDao.saveUser("Johny", "Depp", (byte) 59);
        userDao.saveUser("Angelina", "Jolie", (byte) 47);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }

}
