package com.driver.model;


import javax.persistence.*;

@Entity
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numberOfHours;

    @ManyToOne
    @JoinColumn
    User user;
    @ManyToOne
    @JoinColumn
    Spot spot;
    @OneToOne
    @JoinColumn
    Payment payment;

    public Reservation() {
    }

    public Reservation(int id, int numberOfHours, User user, Spot spot, Payment payment) {
        this.id = id;
        this.numberOfHours = numberOfHours;
        this.user = user;
        this.spot = spot;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public User getUser() {
        return user;
    }

    public Spot getSpot() {
        return spot;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
