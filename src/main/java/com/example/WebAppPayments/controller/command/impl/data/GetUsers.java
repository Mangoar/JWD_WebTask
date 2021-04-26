package com.example.WebAppPayments.controller.command.impl.data;

import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.model.entity.User;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Command to get user list
 */
public class GetUsers implements Command {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(GetUsers.class);

    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        RequestDispatcher requestDispatcher;

        List<User> userList = new ArrayList<>();
        try {
            userList = userService.getAllUsers();

            request.setAttribute(Attribute.USERS, userList);
            requestDispatcher = request.getRequestDispatcher(CommandUrlPath.USERS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.info("GET USERS - SERVICE EXCEPTION", e);
        } catch (ServletException e) {
            logger.info("GET USERS - SERVLET EXCEPTION", e);
        }


    }
}
