package com.leterronapps.invaders;

import com.leterronapps.hyperfour.game.Camera;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Rectangle;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 17/04/15.
 */
public class InvaderStartScene extends HFScene {

    private Sprite playButton;

    public InvaderStartScene(HFGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        camera = new Camera(game, new Vector3D(0,0,0), Camera.MODE_2D, 480, 360);

        Sprite logoSprite = new Sprite(new Vector3D(0,50,0), 360, 180);
        logoSprite.setTexture(InvaderAssets.logo);

        playButton = new Sprite(new Vector3D(0, -100, 0), 156, 78);
        playButton.setTexture(InvaderAssets.playButton);
        playButton.setCollider(new Rectangle(playButton.position, playButton.getWidth(), playButton.getHeight()));

        sceneObjects.add(logoSprite);
        sceneObjects.add(playButton);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
