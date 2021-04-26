package com.example.WebAppPayments.model.service;

import com.example.WebAppPayments.model.entity.User;
import com.example.WebAppPayments.exception.ServiceException;

import java.util.List;

/**
 * The interface user service.
 */
public interface UserService {

    /**
     * Get user by login and password
     * @param login login of user
     * @param password password of user
     * @return user if he exists
     * @throws ServiceException if an dao exception occurred while processing
     */
    User authorization(String login, String password) throws ServiceException;

    /**
     * Add new user
     * @param user new user
     * @throws ServiceException if an dao exception occurred while processing
     */
    void registration(User user) throws ServiceException;

    /**
     * Get list of users
     * @return list of users
     * @throws ServiceException if an dao exception occurred while processing
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * Get user by id
     * @param id id of user
     * @return user if he exists
     * @throws ServiceException if an dao exception occurred while processing
     */
    User getUserById(String id) throws ServiceException;

    /**
     * Unblock new user
     * @param idUser id of user
     * @throws ServiceException if an dao exception occurred while processing
     */
    void unblockUser(int idUser) throws ServiceException;

    /**
     * Block new user
     * @param idUser id of user
     * @throws ServiceException if an dao exception occurred while processing
     */
    void blockUser(int idUser) throws ServiceException;

    /**
     * Update user data
     * @param idUser id of user
     * @param newPasswordString password of user
     * @param newFullnameString fullname of user
     * @param newPassportString passport of user
     * @param newEmailString email of user
     * @throws ServiceException if an dao exception occurred while processing
     */
    void updateUserData(int idUser, String newPasswordString, String newFullnameString, String newPassportString, String newEmailString) throws ServiceException;

    /**
     * Checks if user login is unique
     * @param userLogin login
     * @return boolean
     * @throws ServiceException if an dao exception occurred while processing
     */
    boolean isLoginUnique(String userLogin) throws ServiceException;

    /**
     * Checks if user mail is unique
     * @param userMail mail
     * @return boolean
     * @throws ServiceException if an dao exception occurred while processing
     */
    boolean isMailUnique(String userMail) throws ServiceException;
}
