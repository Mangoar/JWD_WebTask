package com.example.WebAppPayments.controller.ejb;

import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Class used by SendMail servlet to set properties and send message
 */
@Stateless
public class MailSenderBean {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(MailSenderBean.class);

    public void sendEmail(String fromMail, String username, String password,
                          String toEmail, String subject, String message) {

        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);

            Message mailMessage = new MimeMessage(mailSession);


            mailMessage.setFrom(new InternetAddress(fromMail));

            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mailMessage.setContent(message, "text/html");
            mailMessage.setSubject(subject);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", username, password);

            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

            transport.close();
        } catch (Exception e) {
            logger.info("MAILSENDERBEAN EXCEPTION!", e);
        }


    }


}
