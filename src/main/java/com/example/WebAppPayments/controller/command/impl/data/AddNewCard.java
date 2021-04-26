package com.example.WebAppPayments.controller.command.impl.data;

import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.model.entity.Card;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.impl.CardServiceImpl;
import com.example.WebAppPayments.utility.CardCreator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command to add new card
 */
public class AddNewCard implements Command {

    private static final Logger logger = Logger.getLogger(AddNewCard.class);

    private CardServiceImpl cardsService = CardServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        int idAccount = Integer.parseInt(session.getAttribute(Attribute.ID_ACCOUNT).toString());

        Card newCard = CardCreator.createCard(idAccount);

        try {
            while (!cardsService.isCardUnique(newCard.getNumber())) {
                newCard = CardCreator.createCard(idAccount);
            }
        } catch (ServiceException e) {
            logger.info("CHECK UNIQUE CARD - SERVICE EXCEPTION", e);
        }

        try {
            cardsService.addNewCard(newCard);
        } catch (ServiceException e) {
            logger.info("ADD NEW CARD - SERVICE EXCEPTION", e);
        }

        response.sendRedirect(CommandUrlPath.GET_CARDS_COMMAND);

    }
}
