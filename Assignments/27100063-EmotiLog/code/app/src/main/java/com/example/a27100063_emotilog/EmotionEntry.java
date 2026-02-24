// Class: EmotionEntry
// Purpose: Model class representing a single emotion log entry.
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

    @NonNull
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
        return emotion + " at " + dateFormat.format(timestamp);
    }
}
