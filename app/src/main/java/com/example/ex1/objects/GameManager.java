package com.example.ex1.objects;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex1.R;

import java.util.Random;

public class GameManager {
    private GridManager gridManager;
    private Car car;
    private ObstacleManager obstacleManager;
    private LivesManager livesManager;
    private AppCompatActivity activity;
    private boolean gameRunning;
    private MediaPlayer crashSound;
    private int distance;
    private TextView distanceCounter;
    private Random random = new Random();

    private static final int MAX_COLS = 5; // מקסימום עמודות
    private static final int MAX_ROWS = 5; // מקסימום שורות

    public GameManager(AppCompatActivity activity, GridLayout gridLayout, ImageView life1, ImageView life2, ImageView life3, TextView distanceCounter) {
        this.activity = activity;
        gridManager = new GridManager(gridLayout);
        car = new Car();
        livesManager = new LivesManager(life1, life2, life3);
        obstacleManager = new ObstacleManager(gridManager, this);
        gameRunning = true;
        distance = 0;
        this.distanceCounter = distanceCounter;

        // אתחול צליל הקריסה
        crashSound = MediaPlayer.create(activity, R.raw.crash_sound);
    }

    public void startGame() {
        gridManager.setCarPosition(car.getPositionX(), car.getPositionY(), R.drawable.car);
        obstacleManager.startObstacles();
        updateDistanceCounter();
    }

    public void endGame() {
        gameRunning = false;
        obstacleManager.stopObstacles();
        Toast.makeText(activity, "Game Over!", Toast.LENGTH_LONG).show();
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public GridManager getGridManager() {
        return gridManager;
    }

    public Car getCar() {
        return car;
    }

    public LivesManager getLivesManager() {
        return livesManager;
    }

    public void moveCar(int direction) {
        int newX = car.getPositionX() + direction;
        if (newX >= 0 && newX < MAX_COLS && gridManager.canMoveTo(newX, car.getPositionY())) {
            gridManager.clearPosition(car.getPositionX(), car.getPositionY());
            car.setPositionX(newX);
            gridManager.setCarPosition(car.getPositionX(), car.getPositionY(), R.drawable.car);

            // בדיקת התנגשות עם מטבע
            if ("coin".equals(gridManager.getGrid()[car.getPositionY()][car.getPositionX()].getTag())) {
                gridManager.clearCoin(car.getPositionX(), car.getPositionY());
                // אין צורך לקרוא ל-placeCoin כי המטבע יורד כמו מכשול
            }
        }
    }

    public void handleCollision() {
        // ניגון צליל קריסה
        if (crashSound != null) {
            crashSound.start();
        }

        livesManager.loseLife();
        if (livesManager.getLivesCount() == 0) {
            endGame();
        } else {
            resetCarPosition();
        }
    }

    private void resetCarPosition() {
        gridManager.clearPosition(car.getPositionX(), car.getPositionY());
        car.setPositionX(2); // Reset to center (of 5 columns grid)
        car.setPositionY(MAX_ROWS - 1); // Reset to bottom
        gridManager.setCarPosition(car.getPositionX(), car.getPositionY(), R.drawable.car);
    }

    public void pauseGame() {
        if (obstacleManager != null) {
            obstacleManager.pauseObstacles();
        }
    }

    public void stopGame() {
        if (obstacleManager != null) {
            obstacleManager.stopObstacles();
        }
    }

    public void incrementDistance() {
        distance++;
        updateDistanceCounter();
    }

    private void updateDistanceCounter() {
        distanceCounter.setText("Distance: " + distance);
    }
}
