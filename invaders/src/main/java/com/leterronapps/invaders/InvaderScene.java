package com.leterronapps.invaders;

import android.view.MotionEvent;

import com.leterronapps.hyperfour.game.Camera;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.io.InputManager;
import com.leterronapps.hyperfour.util.Circle;
import com.leterronapps.hyperfour.util.CollisionDetector;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 30/03/15.
 */
public class InvaderScene extends HFScene {

    Spaceship ship;
    Shot testShot;

    public InvaderScene(HFGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        camera = new Camera(game, new Vector3D(0,0,0), Camera.MODE_3D, 480, 360);
        camera.rotation.subtract(35, 0, 0);
        ship = new Spaceship(this, new Vector3D(0,-10,-7));
        sceneObjects.add(ship);

        testShot = new Shot(this, new Vector3D(0, -10, -12));
        testShot.setOwner(ship);
        testShot.setMovement(1);
        sceneObjects.add(testShot);

        int alienCount = 0;
        int spawnZ = -35;
        for(int i = 0; i < 5; i++) {
            int spawnX = -1;
            int minX = GameController.getInstance().MIN_X, minOffset = 0;
            int maxX = GameController.getInstance().MAX_X, maxOffset = 20;
            for(int j = 0; j < 5; j++) {
                Invader invader = new Invader(this, new Vector3D(spawnX, -10, spawnZ));
                if(i < 2) {
                    invader.setType(Invader.InvaderType.TYPE_ONE);
                } else {
                    invader.setType(Invader.InvaderType.TYPE_TWO);
                }
                invader.setMinX(minX + minOffset);
                invader.setMaxX(maxX - maxOffset);
                minOffset += 4;
                maxOffset -= 4;
                spawnX += 4;
                sceneObjects.add(invader);
                alienCount++;
            }
            spawnZ -= 10;
        }
        GameController.getInstance().setAliensLeft(alienCount);

        //Sprite logoSprite = new Sprite(new Vector3D(0,0,0), 360, 180);
        //logoSprite.setTexture(InvaderAssets.logo);

        //camera.hud.addItem(logoSprite);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for(MotionEvent event : InputManager.touchEvents.getEvents()) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                ship.shoot();
            }
        }

        for(int i = 0; i < sceneObjects.size(); i++) {
            if(sceneObjects.get(i) instanceof Shot) {
                if(CollisionDetector.spheresColliding((Circle)sceneObjects.get(i).getCollider(), (Circle)ship.getCollider())) {
                    if(((Shot) sceneObjects.get(i)).getOwner() != ship) {
                        sceneObjects.get(i).onCollide(ship);
                    }
                } else {
                    for(int j = 0; j < sceneObjects.size(); j++) {
                        if(sceneObjects.get(j) instanceof Invader) {
                            if(CollisionDetector.spheresColliding((Circle)sceneObjects.get(i).getCollider(), (Circle)sceneObjects.get(j).getCollider())) {
                                if(!(((Shot) sceneObjects.get(i)).getOwner() instanceof Invader)) {
                                    sceneObjects.get(i).onCollide(sceneObjects.get(j));
                                }
                            }
                        }
                    }
                }
            }
        }

    }


}
