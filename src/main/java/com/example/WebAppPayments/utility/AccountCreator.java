package com.example.WebAppPayments.utility;

import com.example.WebAppPayments.model.entity.Account;

import java.util.Random;

/**
 * The class used in account creating
 */
public class AccountCreator {

    public static Account createAccount(int id_user, int id_currency) {
        Account account = new Account();

        Random random = new Random();
        int accNumber = random.nextInt(9000000) + 1000000;
        String accStr = "";
        switch (id_currency) {
            case 1: {
                accStr = "BYN";
                break;
            }
            case 2: {
                accStr = "USD";
                break;
            }
            case 3: {
                accStr = "EUR";
                break;
            }
        }
        String accFullNumber = accStr + accNumber;

        account.setIdUser(id_user);
        account.setAccNumber(accFullNumber);
        account.setIdCurrency(id_currency);


        return account;

    }
}
