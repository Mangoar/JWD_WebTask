package com.example.WebAppPayments.model.dao;

import com.example.WebAppPayments.model.entity.Card;
import com.example.WebAppPayments.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface card dao.
 */
public interface CardDAO {

    /**
     * Extend valid date of card
     * @param newDate new date
     * @param id id of card
     * @throws DaoException if an dao exception occurred while processing
     */
    void extendValidCard(String newDate, int id) throws DaoException;

    /**
     * Get list of cards of cards
     * @param idAccount id of account
     * @return list of cards
     * @throws DaoException if an dao exception occurred while processing
     */
    List<Card> getCardList(int idAccount) throws DaoException;

    /**
     * Add new card
     * @param card new card
     * @throws DaoException if an dao exception occurred while processing
     */
    void addNewCard(Card card) throws DaoException;

    /**
     * Checks if card number is unique
     * @param cardNumber number of card
     * @return boolean
     * @throws DaoException if an dao exception occurred while processing
     */
    boolean isCardUnique(String cardNumber) throws  DaoException;

    /**
     * Delete card
     * @param idCard id of card
     * @throws DaoException if an dao exception occurred while processing
     */
    void deleteCard(int idCard) throws  DaoException;
}
