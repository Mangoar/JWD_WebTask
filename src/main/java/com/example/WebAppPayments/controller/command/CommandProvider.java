package com.example.WebAppPayments.controller.command;

import com.example.WebAppPayments.controller.command.impl.*;
import com.example.WebAppPayments.controller.command.impl.data.*;
import com.example.WebAppPayments.controller.command.impl.locale.ChooseLocaleCommand;
import com.example.WebAppPayments.controller.command.impl.navigation.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GOTOACCOUNTPAGE, new GoToAccountPage());
        commands.put(CommandName.GOTOCARDSPAGE, new GoToCardsPage());
        commands.put(CommandName.GOTOCOMPLETEDPAYMENTPAGE, new GoToCompletedPaymentPage());
        commands.put(CommandName.GOTONEWPAYMENTPAGE, new GoToNewPaymentPage());
        commands.put(CommandName.GOTOPAYMENTPAGE, new GoToPaymentPage());
        commands.put(CommandName.GOTOPROFILEPAGE, new GoToProfilePage());
        commands.put(CommandName.GOTOUSERSPAGE, new GoToUsersPage());


        commands.put(CommandName.GOTOABOUTPAGE, new GoToAboutPage());
        commands.put(CommandName.GOTOCONTACTSPAGE, new GoToContactsPage());
        commands.put(CommandName.GOTOREGISTERPAGE, new GoToRegisterPage());
        commands.put(CommandName.GOTOLOGINPAGE, new GoToLoginPage());
        commands.put(CommandName.REGISTERNEWUSER, new RegisterNewUser());

        commands.put(CommandName.GETCARDS, new GetCards());
        commands.put(CommandName.DELETECARD, new DeleteCard());
        commands.put(CommandName.EXTENDCARDVALIDDATE, new ExtendCardValidDate());

        commands.put(CommandName.GETPAYMENTS, new GetPayments());
        commands.put(CommandName.DELETEACCOUNT, new DeleteAccount());

        commands.put(CommandName.GETACCOUNTS, new GetAccounts());
        commands.put(CommandName.GETPROFILEINFO, new GetProfileInfo());

        commands.put(CommandName.BLOCKUSER, new BlockUser());
        commands.put(CommandName.UNBLOCKUSER, new UnblockUser());
        commands.put(CommandName.GETUSERS, new GetUsers());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.LOGIN, new Login());
        commands.put(CommandName.GOTOMAINPAGE, new GoToMainPage());

        commands.put(CommandName.UPDATEUSERINFO, new UpdateUserInfo());


        commands.put(CommandName.ADDNEWACCOUNT, new AddNewAccount());
        commands.put(CommandName.ADDNEWCARD, new AddNewCard());
        commands.put(CommandName.ADDNEWPAYMENT, new AddNewPayment());

        commands.put(CommandName.CHOOSELOCALECOMMAND, new ChooseLocaleCommand());
    }


    public Command takeCommand(String name) {
        CommandName commandName;
        commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
    }
}
