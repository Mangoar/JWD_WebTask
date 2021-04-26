package com.example.WebAppPayments.controller;

import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.controller.command.Command;
import com.example.WebAppPayments.controller.command.CommandProvider;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Main servlet for proceeding majority of commands
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name;
        Command command;

        session.setAttribute(Attribute.URL, request.getQueryString());

        name = request.getParameter("command");
        command = provider.takeCommand(name);
        command.execute(request, response);

    }
}
