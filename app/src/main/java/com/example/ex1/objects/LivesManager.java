package com.example.ex1.objects;

import android.view.View;
import android.widget.ImageView;

public class LivesManager {
    private int livesCount = 3;
    private ImageView[] lives = new ImageView[3];

    public LivesManager() {

    }

    ;

    public LivesManager(ImageView life1, ImageView life2, ImageView life3) {
        lives[0] = life1;
        lives[1] = life2;
        lives[2] = life3;
    }

    public void loseLife() {
        if (livesCount > 0) {
            livesCount--;
            updateLivesDisplay();
        }
    }

    private void updateLivesDisplay() {
        for (int i = 0; i < lives.length; i++) {
            if (i < livesCount) {
                lives[i].setVisibility(View.VISIBLE);
            } else {
                lives[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    public int getLivesCount() {
        return livesCount;
    }
}
