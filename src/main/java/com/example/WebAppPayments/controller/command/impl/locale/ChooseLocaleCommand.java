package com.example.WebAppPayments.controller.command.impl.locale;

import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandUrlPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command to go to change language page
 */
public class ChooseLocaleCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        String path = request.getParameter("url");
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        if (language.equals(Attribute.LANGUAGE_RU)) {
            session.setAttribute(Attribute.LANGUAGE, Attribute.LANGUAGE_US);
        } else {
            session.setAttribute(Attribute.LANGUAGE, Attribute.LANGUAGE_RU);
        }
        requestDispatcher = request.getRequestDispatcher(CommandUrlPath.CONTROLLER + path);
        requestDispatcher.forward(request, response);
    }
}
