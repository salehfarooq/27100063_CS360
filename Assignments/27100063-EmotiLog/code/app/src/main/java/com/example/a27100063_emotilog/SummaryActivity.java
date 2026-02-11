package com.example.a27100063_emotilog;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        final TextView summaryText = findViewById(R.id.summary_text_view);
        final String stats = DataManager.getInstance().getSummary();

        summaryText.setText(stats);
    }
}