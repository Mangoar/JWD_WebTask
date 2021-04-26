package com.example.WebAppPayments.model.service.impl;

import com.example.WebAppPayments.model.dao.impl.AccountDAOImpl;
import com.example.WebAppPayments.model.entity.Account;
import com.example.WebAppPayments.exception.DaoException;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.AccountService;

import java.util.ArrayList;
import java.util.List;

/**
 * The class for working with the dao for the account
 */
public class AccountServiceImpl implements AccountService {

    private static final AccountServiceImpl instance = new AccountServiceImpl();

    public static AccountServiceImpl getInstance() {
        return instance;
    }

    private AccountServiceImpl() {
    }

    private static final AccountDAOImpl accountDAO = AccountDAOImpl.getInstance();

    @Override
    public List<Account> getAccountList(int idUser) throws ServiceException {
        List<Account> accountList = new ArrayList<>();
        try {
            accountList = accountDAO.getAccountList(idUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return accountList;
    }


    @Override
    public void addNewAccount(Account account) throws ServiceException {
        try {
            accountDAO.addNewAccount(account);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAccount(int idAccount) throws ServiceException {
        try {
            accountDAO.deleteAccount(idAccount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isAccountUnique(String accountNumber) throws ServiceException {
        boolean result = false;
        try {
            result = accountDAO.isAccountUnique(accountNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

}
