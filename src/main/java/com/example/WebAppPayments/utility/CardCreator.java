package com.example.WebAppPayments.utility;

import com.example.WebAppPayments.model.entity.Card;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * The class used in card creating
 */
public class CardCreator {

    private CardCreator() {
    }

    public static Card createCard(int id_account) {
        Card card = new Card();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String validDate = dtf.format(now.plusYears(1));

        Random random = new Random();
        int cvv = random.nextInt(900) + 100;

        int number1 = random.nextInt(9000) + 1000;
        int number2 = random.nextInt(9000) + 1000;
        int number3 = random.nextInt(9000) + 1000;
        int number4 = random.nextInt(9000) + 1000;
        String str_number = "" + number1 + number2 + number3 + number4;

        card.setIdAccount(id_account);
        card.setCvvCode(cvv);
        card.setNumber(str_number);
        card.setValidDate(validDate);


        return card;

    }
}
