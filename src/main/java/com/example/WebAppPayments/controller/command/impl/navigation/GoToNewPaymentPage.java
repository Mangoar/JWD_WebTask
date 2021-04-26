package com.example.WebAppPayments.controller.command.impl.navigation;

import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.utility.validator.DateValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command to go to new payment page
 */
public class GoToNewPaymentPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String validDate = request.getParameter("validdate");

        boolean hasErrors = false;
        if (!DateValidator.isAfterToday(validDate)) {
            hasErrors = true;
        }
        if (hasErrors) {
            response.sendRedirect(CommandUrlPath.GET_CARDS_COMMAND);
        } else {
            session.setAttribute("id_account", session.getAttribute(Attribute.ID_ACCOUNT));
            session.setAttribute("id_card", request.getParameter("idcard"));
            session.setAttribute("number_card", request.getParameter("numbercard"));
            session.setAttribute("balance_card", request.getParameter("balancecard"));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/newPaymentPage.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}