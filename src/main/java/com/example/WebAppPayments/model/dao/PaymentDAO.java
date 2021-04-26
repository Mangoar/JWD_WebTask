package com.example.WebAppPayments.model.dao;

import com.example.WebAppPayments.model.entity.Payment;
import com.example.WebAppPayments.exception.DaoException;

import java.util.List;

/**
 * The interface payment dao.
 */
public interface PaymentDAO {

    /**
     * Add new payment
     * @param payment id of user
     * @throws DaoException if an dao exception occurred while processing
     */
    void addPayment(Payment payment) throws DaoException;

    /**
     * Get list of payments
     * @param userId id of user
     * @return list of payments
     * @throws DaoException if an dao exception occurred while processing
     */
    List<Payment> getPaymentList(int userId) throws DaoException;

    /**
     * Get list of payments
     * @param userId id of user
     * @return list of payments during year
     * @throws DaoException if an dao exception occurred while processing
     */
    List<Payment> getYearPaymentList(int userId) throws DaoException;

    /**
     * Get list of payments
     * @param userId id of user
     * @return list of payments during month
     * @throws DaoException if an dao exception occurred while processing
     */
    List<Payment> getMonthPaymentList(int userId) throws DaoException;

    /**
     * Get list of payments
     * @param userId id of user
     * @return list of payments during week
     * @throws DaoException if an dao exception occurred while processing
     */
    List<Payment> getWeekPaymentList(int userId) throws DaoException;

    /**
     * Get list of payments
     * @param userId id of user
     * @return list of last payments
     * @throws DaoException if an dao exception occurred while processing
     */
    List<Payment> getLastPaymentList(int userId) throws DaoException;
}
