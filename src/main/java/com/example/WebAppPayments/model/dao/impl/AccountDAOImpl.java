package com.example.WebAppPayments.model.dao.impl;

import com.example.WebAppPayments.model.dao.connection.ConnectionPool;
import com.example.WebAppPayments.model.dao.AccountDAO;
import com.example.WebAppPayments.model.entity.Account;
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
 * The class for working with the database for the account
 */
public class AccountDAOImpl implements AccountDAO {

    private static final String SELECT_ACCOUNTLIST_BY_ACC_ID = "SELECT * FROM account WHERE id_user = ?;";
    private static final String SELECT_ACCOUNTLIST_BY_NUMBER = "SELECT * FROM account WHERE acc_number = ?;";
    private static final String DELETE_ACCOUNT = "DELETE FROM account WHERE id = ?;";
    private static final String ADD_ACCOUNT = "INSERT INTO account (id_user, acc_number, id_currency) values (?,?,?);";
    private static final String UPDATE_ACCOUNT = "UPDATE account SET balance = ? WHERE (id = ?);";


    private static final Logger logger = Logger.getLogger(AccountDAOImpl.class);

    private static final AccountDAOImpl instance = new AccountDAOImpl();


    private AccountDAOImpl() {
    }

    public static AccountDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<Account> getAccountList(int idUser) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Account> accountList = new ArrayList<>();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(SELECT_ACCOUNTLIST_BY_ACC_ID);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            accountList = getAccountInfo(resultSet);
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
        return accountList;
    }

    @Override
    public void addNewAccount(Account account) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ACCOUNT);
            preparedStatement.setInt(1, account.getIdUser());
            preparedStatement.setString(2, account.getAccNumber());
            preparedStatement.setInt(3, account.getIdCurrency());
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
    public void deleteAccount(int idAccount) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT);
            preparedStatement.setInt(1, idAccount);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
    }

    @Override
    public void updateBalance(int idAccount, double newValue) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatement.setDouble(1, newValue);
            preparedStatement.setInt(2, idAccount);
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
    public boolean isAccountUnique(String accountNumber) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        boolean result = false;
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(SELECT_ACCOUNTLIST_BY_NUMBER);
            preparedStatement.setString(1, accountNumber);
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

    private List<Account> getAccountInfo(ResultSet resultSet) throws SQLException {
        List<Account> accountList = new ArrayList<>();
        Account accountInfo;
        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString(1));
            String acc_number = resultSet.getString(3);
            double balance = Double.parseDouble(resultSet.getString(4));
            int id_currency = Integer.parseInt(resultSet.getString(5));
            accountInfo = new Account(id, acc_number, balance, id_currency);
            accountList.add(accountInfo);
            logger.info("getaccountinfo " + accountInfo.toString());
        }
        return accountList;
    }
}
