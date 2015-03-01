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
    private int frustumWidth;
    private int frustumHeight;
    public float zoom;

    private Vector3D background;

    public Camera(HFGame game, Vector3D position, int mode, int frustumWidth, int frustumHeight) {
        super(position);
        this.mode = mode;
        this.zoom = 1.0f;
        this.game = game;
        this.frustumWidth = frustumWidth;
        this.frustumHeight = frustumHeight;
        background = new Vector3D(0.2f, 0.2f, 0.2f);
    }

    public void screenToWorldPoint2D(Vector3D touchPoint) {
        touchPoint.x = (touchPoint.x / (float) game.getScreenWidth()) * frustumWidth * zoom;
        touchPoint.y = (1 - touchPoint.y / (float) game.getScreenHeight()) * frustumHeight * zoom;
        touchPoint.add(position).subtract(frustumWidth * zoom / 2, frustumHeight * zoom / 2, 0);
    }

    public void setBackground(Vector3D background) {
        this.background = background;
    }

    public Vector3D getBackground() {
        return background;
    }

    public int getFrustumWidth() {
        return frustumWidth;
    }

    public int getFrustumHeight() {
        return frustumHeight;
    }

    public int getMode() {
        return mode;
    }
}
