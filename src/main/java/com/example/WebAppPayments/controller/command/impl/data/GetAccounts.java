package com.example.WebAppPayments.controller.command.impl.data;

import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.model.entity.Account;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.impl.AccountServiceImpl;
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
 * Command to account list
 */
public class GetAccounts implements Command {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(GetAccounts.class);

    private AccountServiceImpl accountService = AccountServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        RequestDispatcher requestDispatcher;

        HttpSession session = request.getSession();

        int currUserId = Integer.parseInt(session.getAttribute(Attribute.ID_USER).toString());


        List<Account> accountList = new ArrayList<>();

        if (currUserId != 1) {
            try {
                accountList = accountService.getAccountList(currUserId);

                request.setAttribute(Attribute.ACCOUNTS, accountList);

                requestDispatcher = request.getRequestDispatcher(CommandUrlPath.ACCOUNT_PAGE);
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                logger.info("GET ACCOUNTS - SERVLET EXCEPTION", e);
            } catch (ServiceException e) {
                logger.info("GET ACCOUNTS - SERVICE EXCEPTION", e);
            }
        } else {
            try {
                int idUser;

                try {
                    idUser = Integer.parseInt(request.getParameter("iduser"));
                } catch (NumberFormatException e) {
                    idUser = Integer.parseInt(session.getAttribute(Attribute.TEMP_USER).toString());
                }

                session.setAttribute(Attribute.TEMP_USER, idUser);

                accountList = accountService.getAccountList(idUser);

                request.setAttribute(Attribute.ACCOUNTS, accountList);

                requestDispatcher = request.getRequestDispatcher(CommandUrlPath.ACCOUNT_PAGE);
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                logger.info("GET ACCOUNTS - SERVLET EXCEPTION", e);
            } catch (ServiceException e) {
                logger.info("GET ACCOUNTS - SERVICE EXCEPTION", e);
            }
        }

    }
}