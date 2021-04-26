package com.example.WebAppPayments.controller.command.impl.data;

import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.model.entity.Account;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.impl.AccountServiceImpl;
import com.example.WebAppPayments.utility.AccountCreator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command to add new account
 */
public class AddNewAccount implements Command {

    private static final Logger logger = Logger.getLogger(AddNewAccount.class);

    private AccountServiceImpl accountService = AccountServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idUser;
        int idCurrency;

        HttpSession session = request.getSession();
        idUser = Integer.parseInt(session.getAttribute(Attribute.ID_USER).toString());

        idCurrency = Integer.parseInt(request.getParameter("IdCurrency"));

        Account newAccount = AccountCreator.createAccount(idUser, idCurrency);

        try {
            while (!accountService.isAccountUnique(newAccount.getAccNumber())) {
                newAccount = AccountCreator.createAccount(idUser, idCurrency);
            }
        } catch (ServiceException e) {
            logger.info("CHECK UNIQUE ACCOUNT - SERVICE EXCEPTION", e);
        }


        try {
            accountService.addNewAccount(newAccount);
        } catch (ServiceException e) {
            logger.info("ADD NEW ACCOUNT - SERVICE EXCEPTION", e);
        }

        response.sendRedirect(CommandUrlPath.GET_ACCOUNTS_COMMAND);
    }
}
