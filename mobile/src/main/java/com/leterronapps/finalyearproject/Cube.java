package com.leterronapps.finalyearproject;

import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 02/04/15.
 */
public class Cube extends SceneObject {

    private float width = 3f;
    private float height = 3f;
    private float depth = 3f;

    public Cube() {
        super();
        loadVertices();
        setTexture(CoreAssets.scifiPanel);
    }

    public Cube(Vector3D position) {
        super(position);
        loadVertices();
        setTexture(CoreAssets.scifiPanel);
    }

    public Cube(HFScene scene, Vector3D position) {
        super(scene, position);
        loadVertices();
        setTexture(CoreAssets.scifiPanel);
    }

    private void loadVertices() {
        float[] verts = {
                // Front face
                -(width / 2f), (height / 2f), -(depth / 2f),  // index 0
                -(width / 2f), -(height / 2f), -(depth / 2f), // index 1
                (width / 2f), -(height / 2f), -(depth / 2f),  // index 2
                (width / 2f), (height / 2f), -(depth / 2f),   // index 3

                // Back face
                -(width / 2f), (height / 2f), (depth / 2f),   // index 4
                -(width / 2f), -(height / 2f), (depth / 2f),  // index 5
                (width / 2f), -(height / 2f), (depth / 2f),   // index 6
                (width / 2f), (height / 2f), (depth / 2f),    // index 7

                // Left face
                -(width / 2f), (height / 2f), (depth / 2f),   // index 8
                -(width / 2f), -(height / 2f), (depth / 2f),  // index 9
                -(width / 2f), -(height / 2f), -(depth / 2f), // index 10
                -(width / 2f), (height / 2f), -(depth / 2f),  // index 11

                // Right face
                (width / 2f), (height / 2f), (depth / 2f),    // index 12
                (width / 2f), -(height / 2f), (depth / 2f),   // index 13
                (width / 2f), -(height / 2f), -(depth / 2f),  // index 14
                (width / 2f), (height / 2f), -(depth / 2f),   // index 15

                // Top face
                -(width / 2f), (height / 2f), (depth / 2f),   // index 16
                -(width / 2f), (height / 2f), -(depth / 2f),  // index 17
                (width / 2f), (height / 2f), -(depth / 2f),   // index 18
                (width / 2f), (height / 2f), (depth / 2f),    // index 19

                // Bottom face
                -(width / 2f), -(height / 2f), -(depth / 2f), // index 20
                -(width / 2f), -(height / 2f), (depth / 2f),  // index 21
                (width / 2f), -(height / 2f), (depth / 2f),   // index 22
                (width / 2f), -(height / 2f), -(depth / 2f),  // index 23
        };
        float[] norms = {
                // Front face
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,

                // Back face
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,

                // Left face
                -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f,

                // Right face
                1.0f, 0.0f, -1.0f,
                1.0f, 0.0f, -1.0f,
                1.0f, 0.0f, -1.0f,
                1.0f, 0.0f, -1.0f,

                // Top face
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,

                // Bottom face
                0.0f, -1.0f, 0.0f,
                0.0f, -1.0f, 0.0f,
                0.0f, -1.0f, 0.0f,
                0.0f, -1.0f, 0.0f
        };
        float[] texCoords = {
                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f,

                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f,

                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f,

                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f,

                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f,

                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f
        };

        short[] indices = {
                // Front face
                0,1,2,2,3,0,
                // Back face
                4,5,6,6,7,4,
                // Left face
                8,9,10,10,11,8,
                // Right face
                12,13,14,14,15,12,
                // Top face
                16,17,18,18,19,16,
                // Bottom face
                20,21,22,22,23,20
        };
        vertices = new Vertices(verts, norms, texCoords, indices);
    }
}
