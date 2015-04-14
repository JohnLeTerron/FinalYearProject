package com.leterronapps.finalyearproject.game3d;

/**
 * Created by williamlea on 08/04/15.
 */
public class GameController {
    private static GameController ourInstance = new GameController();

    public static GameController getInstance() {
        return ourInstance;
    }

    public final int MIN_X;
    public final int MAX_X;

    private int score;
    private int livesLeft;

    private int aliensLeft;

    private GameController() {
        MIN_X = -20;
        MAX_X = 20;
        score = 0;
        livesLeft = 0;
    }

    public void upScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public void takeLife() {
        livesLeft--;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void setAliensLeft(int aliensLeft) {
        this.aliensLeft = aliensLeft;
    }

    public void alienKilled() {
        aliensLeft--;
    }

}
