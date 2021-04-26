package com.example.WebAppPayments.model.dao;

import com.example.WebAppPayments.model.entity.User;
import com.example.WebAppPayments.exception.ConnectionPoolException;
import com.example.WebAppPayments.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface user dao.
 */
public interface UserDAO{

    /**
     * Add new user
     * @param user new user
     * @throws DaoException if an dao exception occurred while processing
     */
    void addUser(User user) throws ConnectionPoolException, DaoException;

    /**
     * Get user by login and password
     * @param login login of user
     * @param password password of user
     * @return user if he exists
     * @throws DaoException if an dao exception occurred while processing
     */
    User getUserByLoginInfo(String login, String password) throws DaoException;

    /**
     * Get list of users
     * @return list of users
     * @throws DaoException if an dao exception occurred while processing
     */
    List<User> getUserList() throws DaoException;

    /**
     * Block new user
     * @param userId id of user
     * @throws DaoException if an dao exception occurred while processing
     */
    void blockUser(int userId) throws DaoException;

    /**
     * Unblock new user
     * @param userId id of user
     * @throws DaoException if an dao exception occurred while processing
     */
    void unblockUser(int userId) throws DaoException;

    /**
     * Update user data
     * @param userId id of user
     * @param newPasswordString password of user
     * @param newFullnameString fullname of user
     * @param newPassportString passport of user
     * @param newEmailString email of user
     * @throws DaoException if an dao exception occurred while processing
     */
    void updateUserData(int userId, String newPasswordString, String newFullnameString, String newPassportString, String newEmailString) throws DaoException;

    /**
     * Get user by id
     * @param id id of user
     * @return user if he exists
     * @throws DaoException if an dao exception occurred while processing
     */
    User getUserById(String id) throws DaoException;

    /**
     * Checks if user login is unique
     * @param login login
     * @return boolean
     * @throws DaoException if an dao exception occurred while processing
     */
    boolean isLoginUnique(String login) throws  DaoException;

    /**
     * Checks if user mail is unique
     * @param mail mail
     * @return boolean
     * @throws DaoException if an dao exception occurred while processing
     */
    boolean isMailUnique(String mail) throws  DaoException;

    /**
     * Add user photo
     * @param idUser user id
     * @param idPhoto user photo
     * @throws DaoException if an dao exception occurred while processing
     */
    void addPhoto(int idUser, int idPhoto) throws  DaoException;
}
