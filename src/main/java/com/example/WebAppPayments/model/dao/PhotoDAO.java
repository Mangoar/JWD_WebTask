package com.example.WebAppPayments.model.dao;

import com.example.WebAppPayments.exception.ConnectionPoolException;
import com.example.WebAppPayments.exception.DaoException;

public interface PhotoDAO {

    void addPhotoFile(String file, int userId) throws ConnectionPoolException, DaoException;

    int getPhotoId(String file, int userId) throws ConnectionPoolException, DaoException;
}
