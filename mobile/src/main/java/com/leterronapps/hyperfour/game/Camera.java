package com.leterronapps.hyperfour.game;

import com.leterronapps.hyperfour.graphics.HUD;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 23/02/15.
 */
public class Camera extends SceneObject {

    public static final int MODE_2D = 1;
    public static final int MODE_3D = 2;

    private static int MODE;

    private HFGame game;

    private int frustumWidth;
    private int frustumHeight;
    public float zoom;

    public HUD hud;

    public Camera(HFGame game, Vector3D position, int mode, int frustumWidth, int frustumHeight) {
        super(position);
        MODE = mode;
        this.zoom = 1.0f;
        this.game = game;
        this.frustumWidth = frustumWidth;
        this.frustumHeight = frustumHeight;
        hud = new HUD(frustumWidth, frustumHeight);
    }

    public void screenToWorldPoint2D(Vector3D touchPoint) {
        touchPoint.x = (touchPoint.x / (float) game.getScreenWidth()) * frustumWidth * zoom;
        touchPoint.y = (1 - touchPoint.y / (float) game.getScreenHeight()) * frustumHeight * zoom;
        touchPoint.add(position).subtract(frustumWidth * zoom / 2, frustumHeight * zoom / 2, 0);
    }

    public int getFrustumWidth() {
        return frustumWidth;
    }

    public int getFrustumHeight() {
        return frustumHeight;
    }

    public static int getMode() {
        return MODE;
    }
}
