package com.example.WebAppPayments.utility.validator;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class used in user fields validating
 */
public class UserValidator {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(UserValidator.class);

    private static final String LOGIN_PATTERN = "^.[^\\s\\W]{3,20}";
    private static final String FULLNAME_PATTERN = "^.[^\\s\\W]{3,45}";
    private static final String PASSWORD_PATTERN = "^.{6,20}";
    private static final String EMAIL_PATTERN = "[\\w+\\-.]+@[a-z\\d\\-]+(\\.[a-z\\d\\-]+)*\\.[a-z]+";


    private UserValidator() {
    }

    public static boolean isLoginValid(String login) {
        if (login == null || !login.matches(LOGIN_PATTERN)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPasswordValid(String password) {
        if (password == null || !password.matches(PASSWORD_PATTERN)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isEmailValid(String email) {
        if (email == null || !email.matches(EMAIL_PATTERN)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isBirthDateValid(String birthdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean isValid = false;
        try {
            isValid = sdf.parse(birthdate).before(new Date());
        } catch (ParseException e) {
            logger.info("PARSE EXCEPTION WHILE PARSING DATE");
        }
        if (isValid) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isFullnameValid(String fullname) {
        if (fullname == null || !fullname.matches(FULLNAME_PATTERN)) {
            return false;
        } else {
            return true;
        }
    }
}
