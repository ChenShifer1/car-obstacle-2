package com.example.ex1.objects;

import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.ex1.R;

public class GridManager {
    private static final int ROWS = 5; // מספר השורות שונה ל-5
    private static final int COLS = 5; // מספר העמודות נשאר 5

    private ImageView[][] grid = new ImageView[ROWS][COLS];

    public GridManager(GridLayout gridLayout) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = (ImageView) gridLayout.getChildAt(COLS * i + j);
                grid[i][j].setTag(null);
            }
        }
    }

    public void setCarPosition(int x, int y, int drawableId) {
        grid[y][x].setImageResource(drawableId);
        grid[y][x].setTag("car");
    }

    public void clearPosition(int x, int y) {
        grid[y][x].setImageResource(R.drawable.transparent);
        grid[y][x].setTag(null);
    }

    public boolean canMoveTo(int x, int y) {
        return grid[y][x].getTag() == null;
    }

    public void setObstacle(int x, int y, int drawableId) {
        grid[y][x].setImageResource(drawableId);
        grid[y][x].setTag("obstacle");
    }

    public void setCoin(int x, int y, int drawableId) {
        grid[y][x].setImageResource(drawableId);
        grid[y][x].setTag("coin");
    }

    public void clearCoin(int x, int y) {
        if ("coin".equals(grid[y][x].getTag())) {
            grid[y][x].setImageResource(R.drawable.transparent);
            grid[y][x].setTag(null);
        }
    }

    public ImageView[][] getGrid() {
        return grid;
    }
}
