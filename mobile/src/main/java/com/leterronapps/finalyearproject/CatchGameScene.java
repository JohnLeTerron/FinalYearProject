package com.leterronapps.finalyearproject;

import android.view.MotionEvent;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFFont;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.graphics.HFString;
import com.leterronapps.hyperfour.io.InputManager;
import com.leterronapps.hyperfour.util.CollisionDetector;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Rectangle;
import com.leterronapps.hyperfour.util.Vector2D;
import com.leterronapps.hyperfour.util.Vector3D;


/**
 * Created by williamlea on 16/02/15.
 */
public class CatchGameScene extends HFScene {

    private Catcher catcher;
    private Spawner spawner;

    private Sprite pauseButton;

    public GameController controller;

    private HFString timeRemaining;
    private HFString livesLeft;
    private HFString score;

    public CatchGameScene(HFGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        game.getSoundManager().loadMusic(CoreAssets.bgMusic);

        Sprite background = new Sprite(new Vector3D(0,0,0), camera.getFrustumWidth(), camera.getFrustumHeight());
        background.setTexture(CatchAssets.background);

        pauseButton = new Sprite(new Vector3D(-(camera.getFrustumWidth() /2) + 23 ,camera.getFrustumHeight() /2 - 21 ,0), 40, 40);
        pauseButton.setCollider(new Rectangle(pauseButton.position, pauseButton.getWidth(), pauseButton.getHeight()));
        pauseButton.setTexture(CatchAssets.pauseHudButton);

        HFFont font = new HFFont(CoreAssets.font, 10, CoreAssets.font.getWidth() / 10, CoreAssets.font.getHeight() / 10);

        catcher = new Catcher(new Vector3D(0f,-(camera.getFrustumHeight() /2) + 65, 0), 65f, 65f);
        spawner = new Spawner(this, new Vector3D(0, 250, 0));
        controller = new GameController(spawner);

        timeRemaining = new HFString(font, new Vector2D(65,-(camera.getFrustumHeight() / 2) + 20), Integer.toString(controller.getTimeRemaining()));
        livesLeft = new HFString(font, new Vector2D(-(camera.getFrustumWidth() / 2) + 20,-(camera.getFrustumHeight() / 2) + 20), "Lives: " + controller.getLivesLeft());
        score = new HFString(font, new Vector2D(-(camera.getFrustumWidth() / 2) + 20,-(camera.getFrustumHeight() / 2) + 40), "Score: " + controller.getPlayerScore());

        sceneObjects.add(background);
        sceneObjects.add(catcher);
        sceneObjects.add(spawner);
        sceneObjects.add(pauseButton);
    }

    @Override
    public void resume() {
        super.resume();
        game.getSoundManager().playMusic();
    }

    @Override
    public void pause() {
        super.pause();
        game.getSoundManager().pauseMusic();
    }

    @Override
    public void destroy() {
        super.destroy();
        game.getSoundManager().stopMusic();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(playing) {
            updatePlaying(deltaTime);
        } else {
            updatePaused();
        }

        if(controller.getTimeRemaining() <= 0 || controller.getLivesLeft() <= 0) {
            game.setScene(game.getStartScene());
        }
    }

    @Override
    public void render() {
        super.render();
        timeRemaining.render(shader);
        livesLeft.render(shader);
        score.render(shader);
    }

    private void updatePlaying(float deltaTime) {
        controller.tick(deltaTime);
        timeRemaining.setText("Time: " + controller.getTimeRemaining());
        livesLeft.setText("Lives: " + controller.getLivesLeft());
        score.setText("Score: " + controller.getPlayerScore());

        for(int i = 0; i < sceneObjects.size(); i++) {
            if(sceneObjects.get(i) instanceof Ball) {
                if(CollisionDetector.rectanglesColliding((Rectangle)sceneObjects.get(i).getCollider(), (Rectangle)catcher.getCollider())) {
                    sceneObjects.get(i).onCollide(catcher);
                    game.getSoundManager().playSound(CoreAssets.tickSound);
                } else if(sceneObjects.get(i).position.y < -350) {
                    sceneObjects.remove(i);
                }
            } else if(sceneObjects.get(i) instanceof Stopwatch) {
                if(CollisionDetector.rectanglesColliding((Rectangle)sceneObjects.get(i).getCollider(), (Rectangle)catcher.getCollider())) {
                    sceneObjects.get(i).onCollide(catcher);
                } else if(sceneObjects.get(i).position.y < -350) {
                    sceneObjects.remove(i);
                }
            } else if(sceneObjects.get(i) instanceof Bomb) {
                if(CollisionDetector.rectanglesColliding((Rectangle)sceneObjects.get(i).getCollider(), (Rectangle)catcher.getCollider())) {
                    sceneObjects.get(i).onCollide(catcher);
                } else if(sceneObjects.get(i).position.y < -350) {
                    sceneObjects.remove(i);
                }
            }
        }
        Vector3D touchPos;

        for(int i = 0; i < InputManager.touchEvents.size(); i++) {
            MotionEvent event = InputManager.touchEvents.get(i);
            if(event.getAction() == MotionEvent.ACTION_UP) {
                touchPos = new Vector3D(event.getX(), event.getY(), 0f);
                camera.screenToWorldPoint2D(touchPos);
                if(CollisionDetector.pointInRectangle((Rectangle)pauseButton.getCollider(), touchPos)) {
                    game.getSoundManager().playSound(CoreAssets.tickSound);
                    pauseButton.setTexture(CatchAssets.resumeHudButton);
                    playing = false;
                    break;
                }
            }
        }
    }

    private void updatePaused() {
        Vector3D touchPos;

        for(int i = 0; i < InputManager.touchEvents.size(); i++) {
            MotionEvent event = InputManager.touchEvents.get(i);
            if(event.getAction() == MotionEvent.ACTION_UP) {
                touchPos = new Vector3D(event.getX(), event.getY(), 0f);
                camera.screenToWorldPoint2D(touchPos);
                if(CollisionDetector.pointInRectangle((Rectangle)pauseButton.getCollider(), touchPos)) {
                    game.getSoundManager().playSound(CoreAssets.tickSound);
                    pauseButton.setTexture(CatchAssets.pauseHudButton);
                    playing = true;
                    break;
                }
            }
        }
    }

}
