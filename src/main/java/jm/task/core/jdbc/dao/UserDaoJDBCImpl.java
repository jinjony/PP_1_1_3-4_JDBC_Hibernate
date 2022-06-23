package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)");
            connection.commit();
        } catch (SQLException e) {
            try {connection.rollback();} catch (SQLException ex) { throw new RuntimeException(ex);}
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            try {connection.rollback();} catch (SQLException ex) { throw new RuntimeException(ex);}
            e.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)")) {
            connection.setAutoCommit(false);
            stm.setString(1, name);
            stm.setString(2, lastName);
            stm.setByte(3, age);
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {connection.rollback();} catch (SQLException ex) { throw new RuntimeException(ex);}
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            connection.setAutoCommit(false);
            stm.setLong(1, id);
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {connection.rollback();} catch (SQLException ex) { throw new RuntimeException(ex);}
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users")) {
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
//            try {connection.rollback();} catch (SQLException ex) { throw new RuntimeException(ex);}
            e.printStackTrace();
        }
        return users;
    }


    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate("TRUNCATE TABLE users");
            connection.commit();
        } catch (SQLException e) {
            try {connection.rollback();} catch (SQLException ex) { throw new RuntimeException(ex);}
            e.printStackTrace();
        }
    }
}
