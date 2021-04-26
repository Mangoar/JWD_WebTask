package com.example.WebAppPayments.utility.validator;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class used in date validating
 */
public class DateValidator {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(DateValidator.class);

    private DateValidator() {
    }

    public static boolean isAfterToday(String date1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean isValid = false;
        try {
            isValid = sdf.parse(date1).after(new Date());
        } catch (ParseException e) {
            logger.info("PARSE EXCEPTION WHILE PARSING DATE");
        }
        if (isValid) {
            return true;
        } else {
            return false;
        }
    }

}
