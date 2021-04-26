package com.example.WebAppPayments.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * The class of card
 */
public class Card implements Serializable {

    private int id;
    private int idAccount;
    private String number;
    private int cvvCode;
    private String validDate;

    public Card() {
    }

    public Card(int id, int idAccount, String number, int cvvCode, String validDate) {
        this.id = id;
        this.idAccount = idAccount;
        this.number = number;
        this.cvvCode = cvvCode;
        this.validDate = validDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(int cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && idAccount == card.idAccount && cvvCode == card.cvvCode && number.equals(card.number) && validDate.equals(card.validDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idAccount, number, cvvCode, validDate);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", id_account=" + idAccount +
                ", number='" + number + '\'' +
                ", cvv_code=" + cvvCode +
                ", valid_date='" + validDate + '\'' +
                '}';
    }
}
