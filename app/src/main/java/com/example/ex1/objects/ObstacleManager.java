package com.example.ex1.objects;

import android.os.Handler;
import com.example.ex1.R;
import java.util.Random;

public class ObstacleManager {
    private Handler handler = new Handler();
    private Random random = new Random();
    private GridManager gridManager;
    private GameManager gameManager;
    private boolean running = true;

    private static final int MAX_ROWS = 5; // מקסימום שורות
    private static final int MAX_COLS = 5; // מקסימום עמודות

    public ObstacleManager(GridManager gridManager, GameManager gameManager) {
        this.gridManager = gridManager;
        this.gameManager = gameManager;
    }

    public void startObstacles() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    moveObstaclesAndCoinsDown();
                    handler.postDelayed(this, 1000); // Drop obstacle or coin every second
                }
            }
        }, 1000);
    }

    public void stopObstacles() {
        running = false;
        handler.removeCallbacksAndMessages(null); // Remove all pending posts of Runnable
    }

    public void pauseObstacles() {
        running = false;
        handler.removeCallbacksAndMessages(null); // Remove all pending posts of Runnable
    }

    private void moveObstaclesAndCoinsDown() {
        for (int row = MAX_ROWS - 1; row >= 0; row--) {
            for (int col = 0; col < MAX_COLS; col++) {
                String tag = (String) gridManager.getGrid()[row][col].getTag();
                if ("obstacle".equals(tag)) {
                    moveObstacleDown(col, row);
                } else if ("coin".equals(tag)) {
                    moveCoinDown(col, row);
                }
            }
        }
        gameManager.incrementDistance(); // הגדלת המרחק פעם אחת לכל ירידת שורה
        dropObstacleOrCoin();
    }

    private void dropObstacleOrCoin() {
        int col = random.nextInt(MAX_COLS); // שימוש בקבוע עבור מספר העמודות
        boolean dropCoin = random.nextBoolean(); // בחירה אקראית אם לזרוק מטבע או מכשול
        if (dropCoin) {
            gridManager.setCoin(col, 0, R.drawable.coin); // הצבת מטבע בשורה הראשונה
        } else {
            gridManager.setObstacle(col, 0, R.drawable.obstacle); // הצבת מכשול בשורה הראשונה
        }
    }

    private void moveObstacleDown(int col, int row) {
        if (row < MAX_ROWS - 1) { // שימוש בקבוע עבור מספר השורות
            gridManager.clearPosition(col, row);
            if (row + 1 == gameManager.getCar().getPositionY() && col == gameManager.getCar().getPositionX()) {
                gameManager.handleCollision();
            } else {
                gridManager.setObstacle(col, row + 1, R.drawable.obstacle);
            }
        } else {
            gridManager.clearPosition(col, row);
        }
    }

    private void moveCoinDown(int col, int row) {
        if (row < MAX_ROWS - 1) { // שימוש בקבוע עבור מספר השורות
            gridManager.clearPosition(col, row);
            if (row + 1 == gameManager.getCar().getPositionY() && col == gameManager.getCar().getPositionX()) {
                // במקרה של התנגשות עם מטבע, לא קורה כלום
            } else {
                gridManager.setCoin(col, row + 1, R.drawable.coin); // שימוש בקובץ coin.xml
            }
        } else {
            gridManager.clearPosition(col, row);
        }
    }
}
