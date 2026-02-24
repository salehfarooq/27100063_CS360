// Class: DataManager
// Purpose: Singleton controller to manage emotion log data, including persistence.
package com.example.a27100063_emotilog;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class DataManager {
    private static DataManager instance;
    private ArrayList<EmotionEntry> logs;

    private static final String PREFS_NAME = "EmotiLogPrefs";
    private static final String LOGS_KEY = "emotion_logs";
    private transient Gson gson = new Gson();

    private DataManager() {
        logs = new ArrayList<>();
    }

    public static DataManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataManager();
            instance.load(context);
        }
        return instance;
    }

    public void addEntry(String emotion, Context context) {
        EmotionEntry newEntry = new EmotionEntry(emotion);
        logs.add(0, newEntry);
        save(context);
    }

    public ArrayList<EmotionEntry> getLogs() {
        return logs;
    }

    public String getSummary() {
        if (logs == null || logs.isEmpty()) {
            return "No entries yet.";
        }

        Map<String, Map<String, Integer>> dailyCounts = new TreeMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

        for (EmotionEntry entry : logs) {
            String dateKey = dateFormat.format(entry.getTimestamp());
            dailyCounts.putIfAbsent(dateKey, new TreeMap<>());

            Map<String, Integer> emotionCounts = dailyCounts.get(dateKey);
            String emotion = entry.getEmotion();
            emotionCounts.put(emotion, emotionCounts.getOrDefault(emotion, 0) + 1);
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Map<String, Integer>> dateEntry : dailyCounts.entrySet()) {
            result.append("--- ").append(dateEntry.getKey()).append(" ---\n");
            for (Map.Entry<String, Integer> emotionEntry : dateEntry.getValue().entrySet()) {
                result.append(emotionEntry.getKey())
                        .append(": ")
                        .append(emotionEntry.getValue())
                        .append("\n");
            }
            result.append("\n");
        }

        return result.toString();
    }

    private void save(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String json = gson.toJson(logs);
        editor.putString(LOGS_KEY, json);
        editor.apply();
    }

    private void load(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(LOGS_KEY, null);
        if (json != null) {
            Type type = new TypeToken<ArrayList<EmotionEntry>>() {}.getType();
            logs = gson.fromJson(json, type);
        }
        if (logs == null) {
            logs = new ArrayList<>();
        }
    }
}
