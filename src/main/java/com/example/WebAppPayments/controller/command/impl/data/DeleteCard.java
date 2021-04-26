package com.example.WebAppPayments.controller.command.impl.data;

import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.impl.CardServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command to delete card
 */
public class DeleteCard implements Command {

    private static final Logger logger = Logger.getLogger(DeleteCard.class);

    private CardServiceImpl cardService = CardServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idCard;

        idCard = Integer.parseInt(request.getParameter("idcard"));

        try {
            cardService.deleteCard(idCard);
        } catch (ServiceException e) {
            logger.info("ADD NEW ACCOUNT - SERVICE EXCEPTION", e);
        }

        response.sendRedirect(CommandUrlPath.GET_CARDS_COMMAND);
    }
}