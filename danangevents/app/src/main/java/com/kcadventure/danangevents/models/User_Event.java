package com.kcadventure.danangevents.models;

public class User_Event {
    private User user;
    private String status;

    public User_Event() {

    }

    public User_Event(User user, String status) {
        this.user = user;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
