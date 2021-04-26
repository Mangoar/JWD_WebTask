package com.example.WebAppPayments.controller;

import com.example.WebAppPayments.controller.command.Attribute;
import com.example.WebAppPayments.controller.command.CommandUrlPath;
import com.example.WebAppPayments.exception.TransactionException;
import com.example.WebAppPayments.model.dao.Transaction;
import org.apache.log4j.Logger;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.File;
import java.io.IOException;

/**
 * Servlet for uploading photos
 */
@WebServlet("/FileUploadingServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FileUploadingServlet.class);
    private static final String UPLOAD_DIR = "C:\\Users\\User\\IdeaProjects\\WebAppPayments\\src\\main\\webapp\\img\\";

    Transaction transaction = Transaction.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute(Attribute.ID_USER).toString());
        if(!fileName.equals("0")){

            File theDir = new File(UPLOAD_DIR + userId+"\\");
            if (!theDir.exists()){
                theDir.mkdirs();
            }

            for (Part part : request.getParts()) {

                part.write(UPLOAD_DIR + userId+"\\"+ fileName);
            }

            try {
                transaction.addPhoto(userId,fileName);

            } catch (TransactionException ex) {
                logger.info("TRANSACTION EXCEPTION", ex);
            }
        }
        response.sendRedirect(CommandUrlPath.GET_PROFILE_INFO_COMMAND);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
