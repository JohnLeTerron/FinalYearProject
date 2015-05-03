package com.leterronapps.invaders;

import com.leterronapps.hyperfour.game.HFGame;

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
    public final int MAX_Z;
    public final int MIN_Z;

    public final int GAMEOVER_THRESHOLD;

    private int score;
    private int livesLeft;

    private int aliensLeft;

    private HFGame game;

    private GameController() {
        MIN_X = -20;
        MAX_X = 20;
        MIN_Z = -90;
        MAX_Z = 5;
        GAMEOVER_THRESHOLD = -5;
        score = 0;
        livesLeft = 3;
    }

    private GameController(HFGame game) {
        MIN_X = -20;
        MAX_X = 20;
        MIN_Z = -90;
        MAX_Z = 5;
        GAMEOVER_THRESHOLD = -5;
        score = 0;
        livesLeft = 3;
        this.game = game;
    }

    public void init(HFGame game) {
        ourInstance = new GameController(game);
    }

    public void tick(float deltaTime) {
        if(aliensLeft <= 0) {
            game.setScene(new InvaderStartScene(game));
        } else if(livesLeft <= 0) {
            game.setScene(new InvaderStartScene(game));
        }
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
