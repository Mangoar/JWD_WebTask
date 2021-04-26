package com.example.WebAppPayments.controller.command.impl.data;

import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.impl.AccountServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command to delete account
 */
public class DeleteAccount implements Command {

    private static final Logger logger = Logger.getLogger(DeleteAccount.class);

    private AccountServiceImpl accountService = AccountServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idAccount;

        idAccount = Integer.parseInt(request.getParameter("idaccount"));

        try {
            accountService.deleteAccount(idAccount);
        } catch (ServiceException e) {
            logger.info("ADD NEW ACCOUNT - SERVICE EXCEPTION", e);
        }

        response.sendRedirect(CommandUrlPath.GET_ACCOUNTS_COMMAND);
    }
}