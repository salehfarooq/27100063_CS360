package com.example.a27100063_emotilog;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final ListView listView = findViewById(R.id.history_list_view);
        final ArrayList<EmotionEntry> logs = DataManager.getInstance().getLogs();

        final ArrayAdapter<EmotionEntry> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                logs
        );

        listView.setAdapter(adapter);
    }
}