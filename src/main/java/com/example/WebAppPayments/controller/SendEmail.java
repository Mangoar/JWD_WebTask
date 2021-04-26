package com.example.WebAppPayments.controller;

import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.controller.command.Message;
import com.example.WebAppPayments.controller.ejb.MailSenderBean;
import com.example.WebAppPayments.utility.validator.UserValidator;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for sending email messages
 */
@WebServlet(name = "SendEmail", urlPatterns = {"/SendEmail"})
public class SendEmail extends HttpServlet {

    String name, subject, email, msg;

    @EJB
    private MailSenderBean mailSender = new MailSenderBean();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        name = request.getParameter("UserName");
        email = request.getParameter("UserEmail");
        subject = request.getParameter("MailSubject");
        msg = request.getParameter("MailMessage");

        if (!UserValidator.isEmailValid(email)) {
            request.setAttribute(Attribute.MESSAGE_EMAIL, Message.INCORRECT_EMAIL);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/info_pages/contactsPage.jsp");
            requestDispatcher.forward(request, response);
        } else {
            String fullSubject = name + ", " + email + ", " + subject;

            String toEmail = "web.payment.mike.hawk@gmail.com";
            String fromEmail = "web.payment.mike.hawk@gmail.com";
            String username = "web.payment.mike.hawk";
            String password = "!QAZxsw2";

            mailSender.sendEmail(fromEmail, username, password, toEmail, fullSubject, msg);

            request.setAttribute(Attribute.MESSAGE_SEND, Message.MESSAGE_SEND);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/info_pages/contactsPage.jsp");
            requestDispatcher.forward(request, response);
        }


    }
}
