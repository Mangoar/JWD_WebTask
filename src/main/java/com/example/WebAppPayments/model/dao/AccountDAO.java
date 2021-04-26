package com.example.WebAppPayments.model.dao;

import com.example.WebAppPayments.model.entity.Account;
import com.example.WebAppPayments.exception.DaoException;

import java.util.List;

/**
 * The interface account dao.
 */
public interface AccountDAO {

    /**
     * Get list of accounts
     * @param idUser id of user
     * @return list of accounts
     * @throws DaoException if an dao exception occurred while processing
     */
    List<Account> getAccountList(int idUser) throws DaoException;

    /**
     * Add new account
     * @param account new account
     * @throws DaoException if an dao exception occurred while processing
     */
    void addNewAccount(Account account) throws DaoException;

    /**
     * Delete account
     * @param idAccount id of account that will be deleted
     * @throws DaoException if an dao exception occurred while processing
     */
    void deleteAccount(int idAccount) throws DaoException;

    /**
     * Change balance of account
     * @param idAccount id of account
     * @param newValue new balance
     * @return list of accounts
     * @throws DaoException if an dao exception occurred while processing
     */
    void updateBalance(int idAccount, double newValue) throws DaoException;

    /**
     * Checks if account number is unique
     * @param accountNumber account number to check
     * @throws DaoException if an dao exception occurred while processing
     */
    boolean isAccountUnique(String accountNumber) throws DaoException;
}

