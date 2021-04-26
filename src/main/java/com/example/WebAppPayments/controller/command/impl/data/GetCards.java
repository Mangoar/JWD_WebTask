package com.example.WebAppPayments.controller.command.impl.data;

import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.model.entity.Card;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.impl.CardServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Command to get card list
 */
public class GetCards implements Command {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(GetCards.class);

    private CardServiceImpl cardsService = CardServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        RequestDispatcher requestDispatcher;

        int idAccount;
        double balance;

        HttpSession session = request.getSession();

        try {
            idAccount = Integer.parseInt(request.getParameter("idaccount"));
        } catch (NumberFormatException e) {
            idAccount = Integer.parseInt(session.getAttribute(Attribute.ID_ACCOUNT).toString());
        }

        try {
            balance = Double.parseDouble(request.getParameter("balance"));
        } catch (Exception e) {

            balance = Double.parseDouble(session.getAttribute(Attribute.BALANCE).toString());
        }


        logger.info("ID ACCOUNT - " + idAccount);

        List<Card> cardsList = new ArrayList<>();
        try {
            cardsList = cardsService.getCardsList(idAccount);


            session.setAttribute(Attribute.ID_ACCOUNT, idAccount);

            session.setAttribute(Attribute.BALANCE, balance);
            request.setAttribute(Attribute.CARDS, cardsList);
            requestDispatcher = request.getRequestDispatcher(CommandUrlPath.CARDS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.info("GET CARDS - SERVLET EXCEPTION", e);
        } catch (ServiceException e) {
            logger.info("GET CARDS - SERVICE EXCEPTION", e);
        }


    }
}