package com.example.lonelytwitter;

import java.util.Date;

public abstract class Tweet {
    private Date date;
    private String message;

    public Tweet(String message) {
        this.message = message;
        this.date = new Date();
    }
    public Tweet(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract Boolean isImportant();
}