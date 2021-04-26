package com.example.WebAppPayments.model.service;

import com.example.WebAppPayments.model.entity.Account;
import com.example.WebAppPayments.exception.ServiceException;

import java.util.List;

/**
 * The interface account service.
 */
public interface AccountService {

    /**
     * Get list of accounts
     * @param idUser id of user
     * @return list of accounts
     * @throws ServiceException if an dao exception occurred while processing
     */
    List<Account> getAccountList(int idUser) throws ServiceException;

    /**
     * Add new account
     * @param account new account
     * @throws ServiceException if an dao exception occurred while processing
     */
    void addNewAccount(Account account) throws ServiceException;

    /**
     * Delete account
     * @param idAccount id of account that will be deleted
     * @throws ServiceException if an dao exception occurred while processing
     */
    void deleteAccount(int idAccount) throws ServiceException;

    /**
     * Checks if account number is unique
     * @param accountNumber account number to check
     * @throws ServiceException if an dao exception occurred while processing
     */
    boolean isAccountUnique(String accountNumber) throws ServiceException;
}
