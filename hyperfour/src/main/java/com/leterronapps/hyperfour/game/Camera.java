package com.leterronapps.hyperfour.game;

import com.leterronapps.hyperfour.graphics.HUD;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 23/02/15.
 */
public class Camera extends SceneObject {

    /** The constant for setting the camera in 2D rendering mode. */
    public static final int MODE_2D = 1;
    /** The constant for setting the camera in 3D rendering mode. */
    public static final int MODE_3D = 2;

    private static int MODE;

    private HFGame game;

    private int frustumWidth;
    private int frustumHeight;

    /** The zoom level of the camera. */
    public float zoom;

    /** The HUD attached to the camera. */
    public HUD hud;

    /**
     *
     * @param game
     * @param position
     * @param mode
     * @param frustumWidth
     * @param frustumHeight
     */
    public Camera(HFGame game, Vector3D position, int mode, int frustumWidth, int frustumHeight) {
        super(position);
        MODE = mode;
        this.zoom = 1.0f;
        this.game = game;
        this.frustumWidth = frustumWidth;
        this.frustumHeight = frustumHeight;
        hud = new HUD(frustumWidth, frustumHeight);
    }

    /**
     *
     * @param touchPoint
     */
    public void screenToWorldPoint2D(Vector3D touchPoint) {
        touchPoint.x = (touchPoint.x / (float) game.getScreenWidth()) * frustumWidth * zoom;
        touchPoint.y = (1 - touchPoint.y / (float) game.getScreenHeight()) * frustumHeight * zoom;
        touchPoint.add(position).subtract(frustumWidth * zoom / 2, frustumHeight * zoom / 2, 0);
    }

    /**
     *
     * @return The width of the camera's view frustum.
     */
    public int getFrustumWidth() {
        return frustumWidth;
    }

    /**
     *
     * @return The height of the camera's view frustum.
     */
    public int getFrustumHeight() {
        return frustumHeight;
    }

    /**
     *
     * @return The rendering the camera.
     */
    public static int getMode() {
        return MODE;
    }
}
