package com.example.WebAppPayments.controller.command.impl.data;

import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command to unblock user
 */
public class UnblockUser implements Command {

    private static final Logger logger = Logger.getLogger(UnblockUser.class);

    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String idUserString = request.getParameter("iduser");
        int idUser = Integer.parseInt(idUserString);
        try {
            userService.unblockUser(idUser);
        } catch (ServiceException e) {
            logger.info("UNBLOCK USER - SERVICE EXCEPTION", e);
        }
        response.sendRedirect(CommandUrlPath.GET_USERS_COMMAND);
    }
}