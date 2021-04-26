package com.example.WebAppPayments.model.service;

import com.example.WebAppPayments.model.entity.Payment;
import com.example.WebAppPayments.exception.DaoException;
import com.example.WebAppPayments.exception.ServiceException;

import java.util.List;

/**
 * The interface payment service.
 */
public interface PaymentService {

    /**
     * Get list of payments
     * @param userId id of user
     * @return list of payments during year
     * @throws DaoException,ServiceException if an dao exception occurred while processing
     */
    List<Payment> getPaymentsList(int userId) throws DaoException, ServiceException;
}
