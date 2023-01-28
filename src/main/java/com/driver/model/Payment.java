package com.driver.model;

import javax.persistence.*;

@Entity
@Table
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Boolean paymentCompleted = false;
    @Enumerated(value = EnumType.STRING)
    private PaymentMode paymentMode;

    @OneToOne
    @JoinColumn
    private Reservation reservation;

    //Constructor,Getter& setter


    public Payment() {
    }

    public Payment(Boolean paymentCompleted, PaymentMode paymentMode) {
        this.paymentCompleted = paymentCompleted;
        this.paymentMode = paymentMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean isPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(Boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}