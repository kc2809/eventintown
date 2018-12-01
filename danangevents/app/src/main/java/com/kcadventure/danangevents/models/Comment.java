package com.kcadventure.danangevents.models;

public class Comment {

    private int user_id;
    private String comment;

    public Comment() {

    }

    public Comment(int user_id, String comment) {
        this.user_id = user_id;
        this.comment = comment;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
