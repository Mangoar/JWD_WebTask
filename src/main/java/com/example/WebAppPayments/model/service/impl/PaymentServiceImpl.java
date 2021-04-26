package com.example.WebAppPayments.model.service.impl;

import com.example.WebAppPayments.model.dao.impl.PaymentDAOImpl;
import com.example.WebAppPayments.model.entity.Payment;
import com.example.WebAppPayments.exception.DaoException;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.PaymentService;

import java.util.ArrayList;
import java.util.List;

/**
 * The class for working with the dao for the payment
 */
public class PaymentServiceImpl implements PaymentService {

    private static final PaymentServiceImpl instance = new PaymentServiceImpl();

    public static PaymentServiceImpl getInstance() {
        return instance;
    }

    private PaymentServiceImpl() {
    }

    private static final PaymentDAOImpl paymentDAO = PaymentDAOImpl.getInstance();

    @Override
    public List<Payment> getPaymentsList(int userId) throws ServiceException {
        List<Payment> paymentsList = new ArrayList<>();
        try {
            paymentsList = paymentDAO.getPaymentList(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return paymentsList;
    }
}
