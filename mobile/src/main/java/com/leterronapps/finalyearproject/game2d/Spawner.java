package com.leterronapps.finalyearproject.game2d;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Vector3D;

import java.util.Random;

/**
 * Created by williamlea on 26/02/15.
 */
public class Spawner extends SceneObject {

    private float spawnTime = 5.0f;
    private float spawnTick;

    private Random random = new Random();

    public Spawner(Vector3D position) {
        super(position);
    }

    public Spawner(HFScene scene, Vector3D position) {
        super(scene, position);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        position.x = (random.nextFloat() - 0.5f) * 320;
        spawnTick += deltaTime;
        if(spawnTick > spawnTime) {
            spawnTick = 0f;
            spawnBall();
        }
    }

    private void spawnBall() {
        float ballColour = random.nextFloat();
        Log.d(HFGame.DEBUG_TAG, "Ball Spawned");
        Ball ball = new Ball(new Vector3D(position.x, position.y, position.z), 45, 45);
        if(ballColour > 0.5f) {
            ball.setTexture(CatchAssets.ball_wg);
        } else {
            ball.setTexture(CatchAssets.ball_bb);
        }
        scene.getSceneObjects().add(ball);
    }
}
