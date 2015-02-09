package com.leterronapps.hyperfour.game;

import com.leterronapps.hyperfour.graphics.HFShader;
import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 08/02/15.
 */
public class Sprite extends SceneObject {

    private float width;
    private float height;

    public Sprite() {
        super();
    }

    public Sprite(Vector3D position, float width, float height) {
        super(position);
        this.width = width;
        this.height = height;
        loadVertices();
    }

    @Override
    public void update(float deltaTime) {

    }

    private void loadVertices() {
        float[] verts = {
                -(width / 2), -(height / 2), 0f,
                (width / 2), -(height / 2), 0f,
                (width / 2), (height / 2), 0f,
                -(width / 2), (height / 2), 0f
        };
        float[] norms = {
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f
        };
        float[] texCoords = {
                0.0f, 0.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f
        };

        short[] indices = {
                0,1,2,2,3,0
        };
        vertices = new Vertices(verts, norms, texCoords, indices);
    }
}
