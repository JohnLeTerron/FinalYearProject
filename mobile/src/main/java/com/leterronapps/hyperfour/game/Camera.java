package com.leterronapps.hyperfour.game;

import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 23/02/15.
 */
public class Camera extends SceneObject {

    public static final int MODE_2D = 1;
    public static final int MODE_3D = 2;

    private int mode;
    public float zoom;

    public Camera(Vector3D position, int mode) {
        super(position);
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
