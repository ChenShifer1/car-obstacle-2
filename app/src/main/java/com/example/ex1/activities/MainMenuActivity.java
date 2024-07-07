package com.example.ex1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex1.R;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button buttonMode = findViewById(R.id.button_mode);
        Button sensorMode = findViewById(R.id.sensor_mode);
        Button records = findViewById(R.id.records);

        buttonMode.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
            intent.putExtra("mode", "button");
            startActivity(intent);
        });

        sensorMode.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
            intent.putExtra("mode", "sensor");
            startActivity(intent);
        });

        records.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, RecordsActivity.class);
            startActivity(intent);
        });
    }
}
