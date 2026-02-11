package com.example.a27100063_emotilog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class DataManager {
    private static DataManager instance;
    private ArrayList<EmotionEntry> logs;
    private DataManager() {
        logs = new ArrayList<>();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
    public void addEntry(String emotion) {
        EmotionEntry newEntry = new EmotionEntry(emotion);
        logs.add(newEntry);
    }

    public ArrayList<EmotionEntry> getLogs() {
        return logs;
    }

    public String getSummary() {
        HashMap<String, Integer> counts = new HashMap<>();

        for (EmotionEntry entry : logs) {
            String name = entry.getEmotion();
            if (counts.containsKey(name)) {
                counts.put(name, counts.get(name) + 1);
            } else {
                counts.put(name, 1);
            }
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> mapEntry : counts.entrySet()) {
            result.append(mapEntry.getKey())
                    .append(": ")
                    .append(mapEntry.getValue())
                    .append("\n");
        }

        if (result.length() == 0) {
            return "No entries yet.";
        }

        return result.toString();
    }
}
