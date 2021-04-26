package com.example.WebAppPayments.model.service.impl;

import com.example.WebAppPayments.model.dao.impl.CardDAOImpl;
import com.example.WebAppPayments.model.entity.Card;
import com.example.WebAppPayments.exception.DaoException;
import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.CardService;

import java.util.ArrayList;
import java.util.List;

/**
 * The class for working with the dao for the card
 */
public class CardServiceImpl implements CardService {

    private static final CardServiceImpl instance = new CardServiceImpl();

    public static CardServiceImpl getInstance() {
        return instance;
    }

    private CardServiceImpl() {
    }

    private static final CardDAOImpl cardDAO = CardDAOImpl.getInstance();

    @Override
    public void extendCardValidDate(String newDate, int id) throws ServiceException {
        try {
            cardDAO.extendValidCard(newDate, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Card> getCardsList(int idAccount) throws ServiceException {
        List<Card> cardList = new ArrayList<>();
        try {
            cardList = cardDAO.getCardList(idAccount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return cardList;
    }

    @Override
    public void addNewCard(Card card) throws ServiceException {
        try {
            cardDAO.addNewCard(card);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isCardUnique(String cardNumber) throws ServiceException {
        boolean result = false;
        try {
            result = cardDAO.isCardUnique(cardNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public void deleteCard(int idCard) throws ServiceException {
        try {
            cardDAO.deleteCard(idCard);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
