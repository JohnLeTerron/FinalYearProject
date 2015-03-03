package com.leterronapps.finalyearproject.game2d;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
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

    private GameController controller = new GameController();

    private boolean playing = true;

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
        catcher.setTexture(CatchAssets.catcher);

        Spawner spawner = new Spawner(this, new Vector3D(0, 250, 0));

        // HUD Elements
        pauseButton = new Sprite(new Vector3D(-(camera.getFrustumWidth() /2) + 23 ,camera.getFrustumHeight() /2 - 21 ,0), 40, 40);
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
            updatePlaying();
        } else {
            updatePaused();
        }
    }

    private void updatePlaying() {
        for(int i = 0; i < sceneObjects.size(); i++) {
            if(sceneObjects.get(i) instanceof Ball) {
                if(CollisionDetector.rectanglesColliding((Rectangle)sceneObjects.get(i).getCollider(), (Rectangle)catcher.getCollider())){
                    sceneObjects.get(i).onCollide(catcher);
                    game.getSoundManager().playSound(CoreAssets.tickSound);
                    controller.incrementScore();
                    Log.d(HFGame.DEBUG_TAG, "Player Score: " + controller.getPlayerScore());
                    sceneObjects.remove(i);
                } else if(sceneObjects.get(i).position.y < -350) {
                    sceneObjects.remove(i);
                }
            }
        }
    }

    private void updatePaused() {

    }

}
