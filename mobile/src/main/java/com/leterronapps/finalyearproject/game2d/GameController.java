package com.leterronapps.finalyearproject.game2d;

/**
 * Created by williamlea on 02/03/15.
 */
public class GameController {

    private int playerScore;

    public GameController() {

    }

    public void incrementScore() {
        playerScore++;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
