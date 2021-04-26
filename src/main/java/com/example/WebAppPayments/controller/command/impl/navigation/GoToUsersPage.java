package com.example.WebAppPayments.controller.command.impl.navigation;

import com.example.WebAppPayments.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Command to go to users page
 */
public class GoToUsersPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/usersPage.jsp");
        requestDispatcher.forward(request, response);
    }
}