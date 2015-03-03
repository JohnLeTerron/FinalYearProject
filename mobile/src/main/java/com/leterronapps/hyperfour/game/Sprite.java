package com.leterronapps.hyperfour.game;

import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.graphics.HFSubTexture;
import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 08/02/15.
 */
public class Sprite extends SceneObject {

    private float width;
    private float height;

    private HFSubTexture subTexture;

    public Sprite(Vector3D position, float width, float height) {
        super(position);
        this.width = width;
        this.height = height;
        loadVertices();
    }

    public Sprite(HFScene scene, Vector3D position, float width, float height) {
        super(scene, position);
        this.width = width;
        this.height = height;
        loadVertices();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void onCollide(SceneObject other) {
        super.onCollide(other);
    }

    private void loadVertices() {
        float[] verts = {
                -(width / 2f), (height / 2f), 0f,  // index 0
                -(width / 2f), -(height / 2f), 0f, // index 1
                (width / 2f), -(height / 2f), 0f,  // index 2
                (width / 2f), (height / 2f), 0f    // index 3
        };
        float[] norms = {
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f
        };
        float[] texCoords = {
                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f
        };

        short[] indices = {
                0,1,2,2,3,0
        };
        vertices = new Vertices(verts, norms, texCoords, indices);
    }

    private void loadVertices(HFSubTexture subTexture) {
        float[] verts = {
                -(width / 2f), (height / 2f), 0f,  // index 0
                -(width / 2f), -(height / 2f), 0f, // index 1
                (width / 2f), -(height / 2f), 0f,  // index 2
                (width / 2f), (height / 2f), 0f    // index 3
        };
        float[] norms = {
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f
        };
        float[] texCoords = {
                subTexture.u1, subTexture.v1,
                subTexture.u1, subTexture.v2,
                subTexture.u2, subTexture.v2,
                subTexture.u2, subTexture.v1
        };

        short[] indices = {
                0,1,2,2,3,0
        };
        vertices = new Vertices(verts, norms, texCoords, indices);
    }

    public void setSubTexture(HFSubTexture subTexture) {
        this.subTexture = subTexture;
        loadVertices(subTexture);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
