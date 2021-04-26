package com.example.WebAppPayments.controller.command;

import com.example.WebAppPayments.exception.DaoException;
import com.example.WebAppPayments.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * Execute request.
     * @param request the request
     * @throws ServletException,IOException the command exception
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
