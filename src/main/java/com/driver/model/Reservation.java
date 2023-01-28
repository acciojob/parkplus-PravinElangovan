package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name="Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int numberOfHours;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn
    private User user;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn
    private Spot spot;
    @Column(nullable = false)
    @OneToOne
    @JoinColumn
    private Payment Payment;

    public Reservation() {
    }

    public Reservation(int id, int numberOfHours, User user, Spot spot, com.driver.model.Payment payment) {
        this.id = id;
        this.numberOfHours = numberOfHours;
        this.user = user;
        this.spot = spot;
        Payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public com.driver.model.Payment getPayment() {
        return Payment;
    }

    public void setPayment(com.driver.model.Payment payment) {
        Payment = payment;
    }
}
