package com.example.a27100063_emotilog;
import androidx.annotation.NonNull;

import java.util.Date;
import java.text.SimpleDateFormat;
public class EmotionEntry {
    private String emotion;
    private Date timestamp;

    public EmotionEntry(String emotion) {
        this.emotion = emotion;
        this.timestamp = new Date();
    }


    public String getEmotion() {
        return emotion;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return emotion + "-" + timestamp;
    }
}
