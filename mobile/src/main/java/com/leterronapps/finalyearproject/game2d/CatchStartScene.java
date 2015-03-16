package com.leterronapps.finalyearproject.game2d;

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
 * Created by williamlea on 12/02/15.
 */
public class CatchStartScene extends HFScene {

    private Sprite playButton;

    public CatchStartScene(HFGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();

        Sprite background = new Sprite(new Vector3D(0,0,0), camera.getFrustumWidth(), camera.getFrustumHeight());
        background.setTexture(CatchAssets.background);
        Sprite logo = new Sprite(new Vector3D(0f, 150f, 0), camera.getFrustumWidth() - 25, 100f);
        logo.setTexture(CatchAssets.catchLogo);

        playButton = new Sprite(new Vector3D(0f, -115f, 0), camera.getFrustumWidth() - 75, 100f);
        playButton.setCollider(new Rectangle(playButton.position, camera.getFrustumWidth() - 75, 100f));
        playButton.setTexture(CatchAssets.playButton);
        playButton.setSubTexture(CatchAssets.playSubTex);

        sceneObjects.add(background);
        sceneObjects.add(logo);
        sceneObjects.add(playButton);

    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Vector3D touchPos;

        for(int i = 0; i < InputManager.touchEvents.size(); i++) {
            MotionEvent event = InputManager.touchEvents.get(i);
            if(event.getAction() == MotionEvent.ACTION_UP) {
                touchPos = new Vector3D(event.getX(), event.getY(), 0f);
                camera.screenToWorldPoint2D(touchPos);
                if(CollisionDetector.pointInRectangle((Rectangle)playButton.getCollider(), touchPos)) {
                    game.getSoundManager().playSound(CoreAssets.tickSound);
                    game.setScene(new CatchGameScene(game));
                    break;
                }
            }
        }
    }

}
