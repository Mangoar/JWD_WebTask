package com.example.WebAppPayments.model.dao.impl;

import com.example.WebAppPayments.exception.ConnectionPoolException;
import com.example.WebAppPayments.exception.DaoException;
import com.example.WebAppPayments.model.dao.PhotoDAO;
import com.example.WebAppPayments.model.dao.connection.ConnectionPool;
import com.example.WebAppPayments.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoDAOImpl implements PhotoDAO {

    private static final String ADD_PHOTO = "INSERT INTO photos (path) VALUES (?);";
    private static final String SELECT_ID_PHOTO = "SELECT id FROM photos WHERE path = ?;";

    private static final Logger logger = Logger.getLogger(PhotoDAOImpl.class);

    private static final PhotoDAOImpl instance = new PhotoDAOImpl();

    private PhotoDAOImpl() {
    }

    public static PhotoDAOImpl getInstance() {
        return instance;
    }

    @Override
    public void addPhotoFile(String file , int userId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PHOTO);
            preparedStatement.setString(1, "img/"+userId+"/"+file);
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
    public int getPhotoId(String file, int userId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        int id = 1;
        try {

            Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_PHOTO);
            preparedStatement.setString(1, "img/"+userId+"/"+ file);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
            }
            preparedStatement.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            throw new DaoException("Problem with connection to DB occured!", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool occured!", e);
        }
        return id;
    }

}
