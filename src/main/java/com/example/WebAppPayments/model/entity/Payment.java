package com.example.WebAppPayments.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * The class of payment
 */
public class Payment implements Serializable {

    private int id;
    private int idCard;
    private String paymentDate;
    private String idType;
    private double sum;
    private String destination;
    private String cardNumber;

    public Payment() {
    }

    public Payment(int id, int idCard, String paymentDate, String idType, double sum, String destination) {
        this.id = id;
        this.idCard = idCard;
        this.paymentDate = paymentDate;
        this.idType = idType;
        this.sum = sum;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && idCard == payment.idCard && idType == payment.idType && Double.compare(payment.sum, sum) == 0 && paymentDate.equals(payment.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCard, paymentDate, idType, sum);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", id_card=" + idCard +
                ", payment_date='" + paymentDate + '\'' +
                ", id_type=" + idType +
                ", sum=" + sum +
                ", destination='" + destination + '\'' +
                '}';
    }
}
