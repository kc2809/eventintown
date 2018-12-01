package com.kcadventure.danangevents.models;

import java.time.LocalDateTime;

public class Payment {

    private int payment_id;
    private int user_id;
    private double cost;
    private LocalDateTime time;

    public Payment() {

    }

    public Payment(int payment_id, int user_id, double cost, LocalDateTime time) {
        this.payment_id = payment_id;
        this.user_id = user_id;
        this.cost = cost;
        this.time = time;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
