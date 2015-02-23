package com.leterronapps.hyperfour.game;

import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 23/02/15.
 */
public class Camera extends SceneObject {

    public static final int MODE_2D = 1;
    public static final int MODE_3D = 2;

    private HFGame game;

    private int mode;
    private int width;
    private int height;
    public float zoom;

    public Camera(HFGame game, Vector3D position, int mode) {
        super(position);
        this.mode = mode;
        this.zoom = 1.0f;
        this.game = game;
    }

    public void screenToWorldPoint2D(Vector3D touchPoint) {
        touchPoint.x = (touchPoint.x / (float) game.getScreenWidth()) * width * zoom;
        touchPoint.y = (1 - touchPoint.y / (float) game.getScreenHeight()) * height * zoom;
        touchPoint.add(position).subtract(width * zoom / 2, height * zoom / 2, 0);
    }

    public int getMode() {
        return mode;
    }
}
