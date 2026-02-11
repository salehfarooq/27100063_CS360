package com.example.a27100063_emotilog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupEmotionButton(R.id.btnHappy, "Happy");
        setupEmotionButton(R.id.btnSad, "Sad");
        setupEmotionButton(R.id.btnExcited, "Excited");
        setupEmotionButton(R.id.btnAngry, "Angry");
        setupEmotionButton(R.id.btnStressed, "Stressed");
        setupEmotionButton(R.id.btnInLove, "In Love");
        setupEmotionButton(R.id.btnHeartbroken, "Broken");
        setupEmotionButton(R.id.btnSick, "Sick");

        final Button btnHistory = findViewById(R.id.btnViewHistory);
        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        final Button btnSummary = findViewById(R.id.btnViewSummary);
        btnSummary.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            startActivity(intent);
        });
    }

    private void setupEmotionButton(int viewId, final String emotionName) {
        final View button = findViewById(viewId);
        button.setOnClickListener(v -> {
            DataManager.getInstance().addEntry(emotionName);
            Toast.makeText(this, "Logged " + emotionName, Toast.LENGTH_SHORT).show();
        });
    }
}