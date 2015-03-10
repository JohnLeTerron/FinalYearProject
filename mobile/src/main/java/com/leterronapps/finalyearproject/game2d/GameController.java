package com.leterronapps.finalyearproject.game2d;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;

/**
 * Created by williamlea on 02/03/15.
 */
public class GameController {

    private int playerScore;
    private int timeRemaining = 120;
    private int livesLeft = 3;

    private float tick;

    public GameController() {

    }

    public void tick(float deltaTime) {
        tick += deltaTime;
        if(tick > 2.0f) {
            timeRemaining--;
            Log.d(HFGame.DEBUG_TAG, "Time Remaining: " + timeRemaining);
            tick = 0f;
        }
    }

    public void incrementScore() {
        playerScore++;
    }

    public void incrementTime(int time) {
        timeRemaining += time;
    }

    public void decrementLife() {
        livesLeft--;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public int getLivesLeft() {
        return livesLeft;
    }
}
