package com.example.WebAppPayments.model.dao.impl;

import com.example.WebAppPayments.model.dao.connection.ConnectionPool;
import com.example.WebAppPayments.model.dao.CardDAO;
import com.example.WebAppPayments.model.entity.Card;
import com.example.WebAppPayments.exception.ConnectionPoolException;
import com.example.WebAppPayments.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for working with the database for the card
 */
public class CardDAOImpl implements CardDAO {

    private static final String SELECT_CARDLIST = "SELECT * FROM cards;";
    private static final String SELECT_CARDLIST_BY_ACC_ID = "SELECT * FROM cards WHERE id_account = ? order by valid_date asc;";
    private static final String SELECT_CARDLIST_BY_NUMBER = "SELECT * FROM cards WHERE number = ?;";
    private static final String DELETE_CARD = "DELETE FROM cards WHERE id = ?;";
    private static final String ADD_CARD = "INSERT INTO cards (id_account, number, cvv_code, valid_date) values (?,?,?,?)";
    private static final String EXTEND_VALID_CARD = "UPDATE cards SET valid_date = ? where id = ?;";

    private static final CardDAOImpl instance = new CardDAOImpl();


    private CardDAOImpl() {
    }

    public static CardDAOImpl getInstance() {
        return instance;
    }


    @Override
    public void extendValidCard(String newDate, int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EXTEND_VALID_CARD);
            preparedStatement.setString(1, newDate);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
    }

    public List<Card> getCardList(int idAccount) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Card> cardList = new ArrayList<>();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement;
            if (idAccount != 0) {
                preparedStatement = connection.prepareStatement(SELECT_CARDLIST_BY_ACC_ID);
                preparedStatement.setInt(1, idAccount);
            } else {
                preparedStatement = connection.prepareStatement(SELECT_CARDLIST);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            cardList = getCardInfo(resultSet);
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
        return cardList;
    }

    public List<Card> getCardInfo(ResultSet resultSet) throws SQLException {
        List<Card> cardList = new ArrayList<>();
        Card cardInfo;
        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString(1));
            int id_account = Integer.parseInt(resultSet.getString(2));
            String number = resultSet.getString(3);
            int cvv_code = Integer.parseInt(resultSet.getString(4));
            String valid_date = resultSet.getString(5);
            cardInfo = new Card(id, id_account, number, cvv_code, valid_date);
            cardList.add(cardInfo);
        }
        return cardList;
    }

    @Override
    public void addNewCard(Card card) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CARD);
            preparedStatement.setInt(1, card.getIdAccount());
            preparedStatement.setString(2, card.getNumber());
            preparedStatement.setInt(3, card.getCvvCode());
            preparedStatement.setString(4, card.getValidDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
    }

    @Override
    public boolean isCardUnique(String cardNumber) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        boolean result = false;
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(SELECT_CARDLIST_BY_NUMBER);
            preparedStatement.setString(1, cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            pool.releaseConnection(connection);
            if (!resultSet.next()) {
                result = true;
            } else {
                result = false;
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
        return result;
    }

    @Override
    public void deleteCard(int idCard) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARD);
            preparedStatement.setInt(1, idCard);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
    }


}
