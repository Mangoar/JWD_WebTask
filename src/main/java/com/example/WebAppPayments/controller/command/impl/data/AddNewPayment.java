package com.example.WebAppPayments.controller.command.impl.data;

import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.controller.command.Message;
import com.example.WebAppPayments.model.dao.Transaction;
import com.example.WebAppPayments.model.entity.Payment;
import com.example.WebAppPayments.exception.TransactionException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command to add new payment
 */
public class AddNewPayment implements Command {

    private static final Logger logger = Logger.getLogger(AddNewPayment.class);
    Transaction transaction = Transaction.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idAccount;
        double currentValue;
        double paymentValue;
        String destination;
        int idCard;
        String paymentType;
        String paymentValueStr;
        paymentValueStr = request.getParameter("PaymentValue");

        try {
            if (!paymentValueStr.matches("\\d*\\.\\d\\d") && !paymentValueStr.matches("^[0-9]{1,2}$")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/newPaymentPage.jsp");
                request.setAttribute(Attribute.INCORRECT_PAYMENT, Message.INCORRECT_PAYMENT);
                requestDispatcher.forward(request, response);
            }
            else {
                idAccount = Integer.parseInt(request.getParameter("id_account"));
                currentValue = Double.parseDouble(request.getParameter("CurrentBalance"));
                paymentValue = Double.parseDouble(request.getParameter("PaymentValue"));
                destination = request.getParameter("Destination");
                idCard = Integer.parseInt(request.getParameter("id_card"));
                paymentType = request.getParameter("id_payment_type");
                double newValue = 0;
                if (paymentType.equals("-")) {
                    if (currentValue < paymentValue) {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/newPaymentPage.jsp");
                        request.setAttribute(Attribute.NO_FUNDS, Message.NO_FUNDS);
                        requestDispatcher.forward(request, response);
                    } else {
                        newValue = currentValue - paymentValue;
                    }
                } else {
                    newValue = currentValue + paymentValue;
                }


                Payment payment = new Payment();
                payment.setIdType(paymentType);
                payment.setDestination(destination);
                payment.setIdCard(idCard);
                payment.setSum(paymentValue);

                try {
                    transaction.newPayment(idAccount, newValue, payment);

                    response.sendRedirect(CommandUrlPath.COMPLETED_PAYMENT_PAGE_COMMAND);

                } catch (TransactionException ex) {
                    logger.info("TRANSACTION EXCEPTION", ex);
                }
            }
        } catch (ServletException e) {
            logger.info("ADD NEW PAYMENT SERVLET EXCEPTION", e);
        }

    }
}
