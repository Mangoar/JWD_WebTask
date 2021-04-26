package com.example.WebAppPayments.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * The class of account
 */
public class Account implements Serializable {

    private int id;
    private int idUser;
    private String accNumber;
    private double balance;
    private int idCurrency;

    public Account() {
    }

    public Account(int id, String accNumber, double balance, int idCurrency) {
        this.id = id;
        this.accNumber = accNumber;
        this.balance = balance;
        this.idCurrency = idCurrency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(int idCurrency) {
        this.idCurrency = idCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && idUser == account.idUser && Double.compare(account.balance, balance) == 0 && idCurrency == account.idCurrency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, balance, idCurrency);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", id_user=" + idUser +
                ", acc_number='" + accNumber + '\'' +
                ", balance=" + balance +
                ", id_currency=" + idCurrency +
                '}';
    }
}
