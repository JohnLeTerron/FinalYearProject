package com.leterronapps.hyperfour.game;

import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 08/02/15.
 */
public abstract class Sprite extends SceneObject {

    private float width;
    private float height;

    public Sprite(Vector3D position, float width, float height) {
        super(position);
        this.width = width;
        this.height = height;
        loadVertices();
    }

    private void loadVertices() {
        float[] verts = {
                0f, 1f, 0f,  // index 0
                0f, 0f, 0f, // index 1
                1f, 0f, 0f,  // index 2
                1f, 1f, 0f    // index 3
        };
        float[] norms = {
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f
        };
        float[] texCoords = {
                0f, 1f,
                0f, 0f,
                1f, 0f,
                1f, 1f
        };

        short[] indices = {
                0,1,2,0,2,3
        };
        vertices = new Vertices(verts, norms, texCoords, indices);
    }
}
