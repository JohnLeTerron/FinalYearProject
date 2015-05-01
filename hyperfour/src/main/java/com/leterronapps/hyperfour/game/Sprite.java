package com.leterronapps.hyperfour.game;

import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.graphics.HFSubTexture;
import com.leterronapps.hyperfour.graphics.SpriteAnimation;
import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * A sub-class of SceneObject used for 2D games.
 * Sub-class Sprite to make your own Sprite objects with custom behaviours.
 */
public class Sprite extends SceneObject {

    private float width;
    private float height;

    private HFSubTexture subTexture;

    /** The flip-book animation for the Sprite. */
    protected SpriteAnimation anim;

    /**
     * Constructs a new empty Sprite with no geometry.
     */
    public Sprite() {
        super();
        width = 1.0f;
        height = 1.0f;
    }

    /**
     * Constructs a new Sprite with an initial position, width and height.
     * @param position The initial position of the Sprite.
     * @param width The initial width of the Sprite.
     * @param height The initial height of the Sprite.
     */
    public Sprite(Vector3D position, float width, float height) {
        super(position);
        this.width = width;
        this.height = height;
        loadVertices();
    }

    /**
     * Constructs a new Sprite with an initial position, width and height and the scene that contains the Sprite.
     * @param scene The scene that contains the Sprite.
     * @param position The initial position of the Sprite.
     * @param width The initial width of the Sprite.
     * @param height The initial height of the Sprite.
     */
    public Sprite(HFScene scene, Vector3D position, float width, float height) {
        super(scene, position);
        this.width = width;
        this.height = height;
        loadVertices();
    }

    /**
     * Defines the frame by frame behaviour of the Sprite.
     * Override to add custom behaviour.
     * @param deltaTime The time difference between frames. Used to achieve frame-rate independent motion.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    /**
     * Called when two SceneObjects collide with each other.
     * Override to add extra collision behaviour.
     * @param other The other SceneObject the SceneObject collided with.
     */
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

    /**
     * Sets a new sub-texture for the Sprite.
     * @param subTexture The new sub-texture for the Sprite.
     */
    public void setSubTexture(HFSubTexture subTexture) {
        this.subTexture = subTexture;
        loadVertices(subTexture);
    }

    /**
     * Sets a new SpriteAnimation for the Sprite.
     * @param anim The new SpriteAnimation for the Sprite.
     */
    public void setAnimation(SpriteAnimation anim) {
        this.anim = anim;
        this.anim.setup(texture);
    }

    /**
     *
     * @return The width of the Sprite.
     */
    public float getWidth() {
        return width;
    }

    /**
     *
     * @return The height of the Sprite.
     */
    public float getHeight() {
        return height;
    }
}
