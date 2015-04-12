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

    private GameController() {
        MIN_X = -12;
        MAX_X = 12;
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

}
