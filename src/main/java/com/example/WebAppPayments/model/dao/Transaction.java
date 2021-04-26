package com.example.WebAppPayments.model.dao;

import com.example.WebAppPayments.model.dao.connection.ConnectionPool;
import com.example.WebAppPayments.model.dao.impl.AccountDAOImpl;
import com.example.WebAppPayments.model.dao.impl.PaymentDAOImpl;
import com.example.WebAppPayments.model.dao.impl.PhotoDAOImpl;
import com.example.WebAppPayments.model.dao.impl.UserDAOImpl;
import com.example.WebAppPayments.model.entity.Payment;
import com.example.WebAppPayments.exception.ConnectionPoolException;
import com.example.WebAppPayments.exception.DaoException;
import com.example.WebAppPayments.exception.TransactionException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The class for working with the database(updates more than 1 tables)
 */
public class Transaction {

    private static final Logger logger = Logger.getLogger(Transaction.class);

    private static final Transaction instance = new Transaction();

    private Transaction() {
    }

    public static Transaction getInstance() {
        return instance;
    }

    private static final AccountDAOImpl accountDAO = AccountDAOImpl.getInstance();
    private static final PaymentDAOImpl paymentDAO = PaymentDAOImpl.getInstance();
    private static final UserDAOImpl userDAO = UserDAOImpl.getInstance();
    private static final PhotoDAOImpl photoDAO = PhotoDAOImpl.getInstance();

    public void newPayment(int idAccount, double newValue, Payment payment) throws TransactionException {

        Connection connection = null;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            connection.setAutoCommit(false);
            accountDAO.updateBalance(idAccount, newValue);
            paymentDAO.addPayment(payment);
        } catch (SQLException | DaoException | ConnectionPoolException e) {
            rollbackConnection(connection);
            throw new TransactionException("Can't proceed payment", e);
        } finally {
            closeConnection(connection);
        }

    }

    public void addPhoto(int idUser, String file) throws TransactionException {

        Connection connection = null;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            connection.setAutoCommit(false);

            photoDAO.addPhotoFile(file, idUser);
            //
            int idPhoto = photoDAO.getPhotoId(file ,idUser);
            userDAO.addPhoto(idUser, idPhoto);

        } catch (SQLException | DaoException | ConnectionPoolException e) {
            rollbackConnection(connection);
            throw new TransactionException("Can't proceed payment", e);
        } finally {
            closeConnection(connection);
        }

    }

    private void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error("Error while rollback transaction: ", e);
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.error("Error while closing connection: ", e);
            }
        }
    }


}