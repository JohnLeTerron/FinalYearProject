package com.leterronapps.finalyearproject.game2d;

import android.util.Log;
import android.view.MotionEvent;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.io.InputManager;
import com.leterronapps.hyperfour.util.CollisionDetector;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Rectangle;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 16/02/15.
 */
public class CatchGameScene extends HFScene {

    private Catcher catcher;

    private Sprite pauseButton;

    public GameController controller = new GameController();

    public CatchGameScene(HFGame game) {
        super(game);
    }

    @Override
    public void resume() {
        super.resume();
        // Scene Elements
        Sprite background = new Sprite(new Vector3D(0,0,0), camera.getFrustumWidth(), camera.getFrustumHeight());
        background.setTexture(CatchAssets.background);

        catcher = new Catcher(new Vector3D(0f,-(camera.getFrustumHeight() /2) + 65, 0), 65f, 65f);

        Spawner spawner = new Spawner(this, new Vector3D(0, 250, 0));

        // HUD Elements
        pauseButton = new Sprite(new Vector3D(-(camera.getFrustumWidth() /2) + 23 ,camera.getFrustumHeight() /2 - 21 ,0), 40, 40);
        pauseButton.setCollider(new Rectangle(pauseButton.position, pauseButton.getWidth(), pauseButton.getHeight()));
        pauseButton.setTexture(CatchAssets.pauseHudButton);

        sceneObjects.add(background);
        sceneObjects.add(catcher);
        sceneObjects.add(spawner);
        sceneObjects.add(pauseButton);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(playing) {
            updatePlaying(deltaTime);
        } else {
            updatePaused();
        }

        if(controller.getTimeRemaining() <= 0) {
            game.setScene(game.getStartScene());
        }
    }

    private void updatePlaying(float deltaTime) {
        controller.tick(deltaTime);
        for(int i = 0; i < sceneObjects.size(); i++) {
            if(sceneObjects.get(i) instanceof Ball) {
                if(CollisionDetector.rectanglesColliding((Rectangle)sceneObjects.get(i).getCollider(), (Rectangle)catcher.getCollider())) {
                    sceneObjects.get(i).onCollide(catcher);
                    game.getSoundManager().playSound(CoreAssets.tickSound);
                    Log.d(HFGame.DEBUG_TAG, "Player Score: " + controller.getPlayerScore());
                    sceneObjects.remove(i);
                } else if(sceneObjects.get(i).position.y < -350) {
                    sceneObjects.remove(i);
                }
            } else if(sceneObjects.get(i) instanceof Stopwatch) {
                if(CollisionDetector.rectanglesColliding((Rectangle)sceneObjects.get(i).getCollider(), (Rectangle)catcher.getCollider())) {
                    sceneObjects.get(i).onCollide(catcher);
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
                }
            }
        }
    }

}
