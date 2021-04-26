package com.example.WebAppPayments.controller.command.impl.data;

import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.controller.command.Message;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.impl.UserServiceImpl;
import com.example.WebAppPayments.utility.validator.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command to update user info
 */
public class UpdateUserInfo implements Command {

    private static final Logger logger = Logger.getLogger(UpdateUserInfo.class);

    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String idUserString = session.getAttribute(Attribute.ID_USER).toString();

        String newPasswordString = request.getParameter("NewUserPassword");
        String newFullnameString = request.getParameter("NewUserFullname");
        String newPassportString = request.getParameter("NewUserPassport");
        String newEmailString = request.getParameter("NewUserEmail");
        int idUser = Integer.parseInt(idUserString);


        boolean hasErrors = false;
        try {
            if (!UserValidator.isPasswordValid(newPasswordString)) {
                request.setAttribute(Attribute.MESSAGE_PASSWORD, Message.INCORRECT_PASSWORD);
                hasErrors = true;
            }
            if (!UserValidator.isEmailValid(newEmailString)) {
                request.setAttribute(Attribute.MESSAGE_EMAIL, Message.INCORRECT_EMAIL);
                hasErrors = true;
            }
            if (!UserValidator.isFullnameValid(newFullnameString)) {
                request.setAttribute(Attribute.MESSAGE_FULLNAME, Message.INCORRECT_FULLNAME);
                hasErrors = true;
            }
            if (hasErrors) {
                response.sendRedirect(CommandUrlPath.GET_PROFILE_INFO_COMMAND);
            } else {
                userService.updateUserData(idUser, newPasswordString, newFullnameString, newPassportString, newEmailString);
                response.sendRedirect(CommandUrlPath.GET_PROFILE_INFO_COMMAND);
            }
        } catch (ServiceException e) {
            logger.info("UPDATE USER INFO - SERVICE EXCEPTION", e);
        }


    }
}
