package com.example.WebAppPayments.model.dao.impl;

import com.example.WebAppPayments.model.dao.connection.ConnectionPool;
import com.example.WebAppPayments.model.dao.UserDAO;
import com.example.WebAppPayments.model.entity.User;
import com.example.WebAppPayments.exception.ConnectionPoolException;
import com.example.WebAppPayments.exception.DaoException;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for working with the database for the user
 */
public class UserDAOImpl implements UserDAO {

    private static final String ADD_USER = "INSERT INTO user_acc " +
            "(login,password,fullname,birthdate,passport,email) " +
            "VALUES (?,?,?,?,?,?)";
    private static final String SELECT_USER = "SELECT * FROM user_acc " +
            "WHERE login = ? and password = ? having blocked<1";
    private static final String SELECT_USER_BY_ID = "SELECT u.*, p.path  FROM user_acc u, photos p WHERE u.id = ? and p.id = u.id_photo;";
    private static final String SELECT_USERLIST_BY_LOGIN = "SELECT * FROM user_acc " +
            "WHERE login = ? ;";
    private static final String SELECT_USERLIST_BY_MAIL = "SELECT * FROM user_acc " +
            "WHERE email = ? ;";
    private static final String SELECT_USERLIST = "SELECT * FROM user_acc;";
    private static final String BLOCK_USER = "UPDATE user_acc SET blocked = 1 WHERE" +
            " (id = ?);";
    private static final String UNBLOCK_USER = "UPDATE user_acc SET blocked = 0 WHERE" +
            " (id = ?);";
    private static final String UPDATE_PHOTO = "UPDATE user_acc SET id_photo = ? WHERE" +
            " (id = ?);";
    private static final String UPDATE_USER_DATE = "UPDATE user_acc SET password = ?," +
            " fullname = ?, passport = ?, email = ? WHERE (id = ?);";

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private static final UserDAOImpl instance = new UserDAOImpl();

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance() {
        return instance;
    }


    @Override
    public void addUser(User user) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullname());
            preparedStatement.setString(4, user.getBirthdate());
            preparedStatement.setString(5, user.getPassport());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
    }

    @Override
    public User getUserByLoginInfo(String login, String password) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        User user = null;
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                user = getUserInfo(resultSet).get(0);
            } catch (IndexOutOfBoundsException ex) {
                logger.info("NO USER IN DAO IMPL");
            }
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
        return user;
    }

    public List<User> getUserInfo(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        User userInfo;
        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString(1));
            int id_role = Integer.parseInt(resultSet.getString(2));
            String login = resultSet.getString(4);
            String password = resultSet.getString(5);
            String fullname = resultSet.getString(6);
            String birthdate = resultSet.getString(7);
            String passport = resultSet.getString(8);
            String email = resultSet.getString(9);
            int blocked = Integer.parseInt(resultSet.getString(10));
            userInfo = new User(id, id_role, 0, login, password, fullname, birthdate, passport, email, blocked);
            userList.add(userInfo);
        }
        return userList;
    }

    public List<User> getUserInfoWPhoto(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        User userInfo;
        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString(1));
            int id_role = Integer.parseInt(resultSet.getString(2));
            String login = resultSet.getString(4);
            String password = resultSet.getString(5);
            String fullname = resultSet.getString(6);
            String birthdate = resultSet.getString(7);
            String passport = resultSet.getString(8);
            String email = resultSet.getString(9);
            int blocked = Integer.parseInt(resultSet.getString(10));
            String photoPath = resultSet.getString(11);
            userInfo = new User(id, id_role, 0, login, password, fullname, birthdate, passport, email, blocked);
            userInfo.setPhotoPath(photoPath);
            userList.add(userInfo);
        }
        return userList;
    }

    @Override
    public List<User> getUserList() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERLIST);
            ResultSet resultSet = preparedStatement.executeQuery();
            userList = getUserInfo(resultSet);
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
        return userList;
    }

    @Override
    public void blockUser(int userId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
    }

    @Override
    public void unblockUser(int userId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UNBLOCK_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
    }

    @Override
    public void updateUserData(int userId, String newPasswordString, String newFullnameString, String newPassportString, String newEmailString) throws DaoException {
        logger.info("IN PARAMS - " + userId + " " + newPasswordString + " " + newFullnameString + " " + newPassportString + " " + newEmailString);

        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_DATE);
            preparedStatement.setString(1, newPasswordString);
            preparedStatement.setString(2, newFullnameString);
            preparedStatement.setString(3, newPassportString);
            preparedStatement.setString(4, newEmailString);
            preparedStatement.setInt(5, userId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
    }

    @Override
    public User getUserById(String id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        User user = null;
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = getUserInfoWPhoto(resultSet).get(0);
            if (user == null) {
                logger.info("NO USER IN DAO IMPL");
            }
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
        return user;
    }

    @Override
    public boolean isLoginUnique(String login) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        boolean result = false;
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(SELECT_USERLIST_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            pool.releaseConnection(connection);
            if (!resultSet.next()) {
                result = true;
            } else {
                result = false;
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
        return result;
    }

    @Override
    public boolean isMailUnique(String mail) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        boolean result = false;
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(SELECT_USERLIST_BY_MAIL);
            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            pool.releaseConnection(connection);
            if (!resultSet.next()) {
                result = true;
            } else {
                result = false;
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
        return result;
    }

    @Override
    public void addPhoto(int idUser, int idPhoto) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHOTO);
            preparedStatement.setInt(1, idPhoto);
            preparedStatement.setInt(2, idUser);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
    }
}
