package com.example.WebAppPayments.model.service;

import com.example.WebAppPayments.model.entity.Card;
import com.example.WebAppPayments.exception.ServiceException;

import java.util.List;

/**
 * The interface card service.
 */
public interface CardService {

    /**
     * Extend valid date of card
     * @param newDate new date
     * @param id id of card
     * @throws ServiceException if an dao exception occurred while processing
     */
    void extendCardValidDate(String newDate, int id) throws ServiceException;

    /**
     * Get list of cards of cards
     * @param idAccount id of account
     * @return list of cards
     * @throws ServiceException if an dao exception occurred while processing
     */
    List<Card> getCardsList(int idAccount) throws ServiceException;

    /**
     * Add new card
     * @param card new card
     * @throws ServiceException if an dao exception occurred while processing
     */
    void addNewCard(Card card) throws ServiceException;

    /**
     * Checks if card number is unique
     * @param cardNumber number of card
     * @return boolean
     * @throws ServiceException if an dao exception occurred while processing
     */
    boolean isCardUnique(String cardNumber) throws ServiceException;

    /**
     * Delete card
     * @param idCard id of card
     * @throws ServiceException if an dao exception occurred while processing
     */
    void deleteCard(int idCard) throws ServiceException;
}
