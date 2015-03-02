package com.leterronapps.finalyearproject.game2d;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.CollisionDetector;
import com.leterronapps.hyperfour.util.Rectangle;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 16/02/15.
 */
public class CatchGameScene extends HFScene {

    private Catcher catcher;
    private Spawner spawner;

    private GameController controller = new GameController();

    public CatchGameScene(HFGame game) {
        super(game);
    }

    @Override
    public void resume() {
        super.resume();
        Sprite background = new Sprite(new Vector3D(0,0,-1), camera.getFrustumWidth(), camera.getFrustumHeight());
        background.setTexture(CatchAssets.background);

        catcher = new Catcher(new Vector3D(0f,-(camera.getFrustumHeight() /2) + 65, 1), 50f, 50f);
        catcher.setTexture(CatchAssets.catcher);

        spawner = new Spawner(this, new Vector3D(0, 250, 0));

        sceneObjects.add(catcher);
        sceneObjects.add(spawner);
        sceneObjects.add(background);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for(int i = 0; i < sceneObjects.size(); i++) {
           if(sceneObjects.get(i) instanceof Ball) {
               if(CollisionDetector.rectanglesColliding((Rectangle)sceneObjects.get(i).getCollider(), (Rectangle)catcher.getCollider())){
                   sceneObjects.get(i).onCollide(catcher);
                   controller.incrementScore();
                   Log.d(HFGame.DEBUG_TAG, "Player Score: " + controller.getPlayerScore());
                   sceneObjects.remove(i);
               } else if(sceneObjects.get(i).position.y < -350) {
                   sceneObjects.remove(i);
               }
           }
        }
    }
}
