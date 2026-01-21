package com.example.lonelytwitter;

import java.util.Date;

public class SadMood extends Mood {
    public SadMood(Date date) {
        super(date);
    }
    public SadMood() {
        super();
    }

    @Override
    public String getMood() {
        return "Sad:(";
    }
}
