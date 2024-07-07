package com.example.ex1;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ex1.objects.GameManager;

public class UIController {
    private AppCompatActivity activity;

    public UIController (){

    };

    public UIController(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setupButtons(Button leftButton, Button rightButton, GameManager gameManager) {
        leftButton.setOnClickListener(v -> {
            if (gameManager.isGameRunning()) {
                gameManager.moveCar(-1);
            }
        });
        rightButton.setOnClickListener(v -> {
            if (gameManager.isGameRunning()) {
                gameManager.moveCar(1);
            }
        });
    }
}
