package com.example.WebAppPayments.controller.command;

/**
 * Constants for addresses
 */
public class CommandUrlPath {

    public final static String ACCOUNT_PAGE = "/WEB-INF/views/accountPage.jsp";
    public final static String CARDS_PAGE = "/WEB-INF/views/cardsPage.jsp";
    public final static String COMPLETED_PAYMENT_PAGE = "/WEB-INF/views/completedPaymentPage.jsp";
    public final static String PAYMENT_PAGE = "/WEB-INF/views/paymentPage.jsp";
    public final static String PROFILE_PAGE = "/WEB-INF/views/temp_pages/profilePage.jsp";
    public final static String USERS_PAGE = "/WEB-INF/views/usersPage.jsp";
    public final static String LOGIN_PAGE = "/WEB-INF/views/loginPage.jsp";
    public final static String REGISTRATION_PAGE = "/WEB-INF/views/registrationPage.jsp";
    public final static String MAIN_PAGE = "/WEB-INF/views/mainPage.jsp";

    public final static String ACCOUNT_PAGE_COMMAND = "Controller?command=gotoaccountpage";
    public final static String CARDS_PAGE_COMMAND = "Controller?command=gotocardspage";
    public final static String USERS_PAGE_COMMAND = "Controller?command=gotouserspage";
    public final static String LOGIN_PAGE_COMMAND = "Controller?command=gotologinpage";
    public final static String MAIN_PAGE_COMMAND = "Controller?command=gotomainpage";
    public final static String PAYMENT_PAGE_COMMAND = "Controller?command=gotopaymentpage";
    public final static String NEW_PAYMENT_PAGE_COMMAND = "Controller?command=gotonewpaymentpage";
    public final static String REGISTER_PAGE_COMMAND = "Controller?command=gotoregisterpage";
    public final static String PROFILE_PAGE_COMMAND = "Controller?command=gotoprofilepage";
    public final static String COMPLETED_PAYMENT_PAGE_COMMAND = "Controller?command=gotocompletedpaymentpage";

    public final static String GET_PROFILE_INFO_COMMAND = "Controller?command=getprofileinfo";
    public final static String GET_USERS_COMMAND = "Controller?command=getusers";
    public final static String GET_CARDS_COMMAND = "Controller?command=getcards";
    public final static String GET_ACCOUNTS_COMMAND = "Controller?command=getaccounts";

    public final static String CONTROLLER = "Controller?";
}
