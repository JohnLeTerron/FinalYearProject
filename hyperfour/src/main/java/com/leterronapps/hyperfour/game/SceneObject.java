package com.leterronapps.hyperfour.game;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.graphics.HFShader;
import com.leterronapps.hyperfour.graphics.HFTexture;
import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.Collider;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * The base class for all objects that can be placed in a scene. This class is abstract and should be
 * sub-classed to make custom scene objects within your game.
 */
public abstract class SceneObject {

    /** The position of the scene object. */
    public Vector3D position;

    /** The rotation of the scene object. */
    public Vector3D rotation;

    /** The scale of the scene object. */
    public Vector3D scale;

    /** The vertex data for the scene object. */
    protected Vertices vertices;

    /** The texture to be skinned over the vertices of the scene object. */
    protected HFTexture texture;

    /** The collision component of the scene object. */
    protected Collider collider;

    /** The scene containing the scene object. */
    protected HFScene scene;

    private boolean markedForDeath = false;

    /**
     * Constructs a new SceneObject.
     */
    public SceneObject() {
        position = new Vector3D();
        rotation = new Vector3D();
        scale = new Vector3D(1.0f, 1.0f, 1.0f);
    }

    /**
     * Constructs a new SceneObject with an initial position.
     * @param position The initial position of the SceneObject.
     */
    public SceneObject(Vector3D position) {
        this.position = position;
        rotation = new Vector3D();
        scale = new Vector3D(1.0f, 1.0f, 1.0f);
    }

    /**
     * Constructs a new SceneObject with an initial position and a specified scene that contains the scene object.
     * @param scene The scene containing the SceneObject.
     * @param position The initial position of the SceneObject.
     */
    public SceneObject(HFScene scene, Vector3D position) {
        this.scene = scene;
        this.position = position;
        rotation = new Vector3D();
        scale = new Vector3D(1.0f, 1.0f, 1.0f);
    }

    /**
     * Defines the frame by frame behaviour of the SceneObject.
     * Override to add custom behaviour.
     * @param deltaTime The time difference between frames. Used to achieve frame-rate independent motion.
     */
    public void update(float deltaTime) {
        if(collider != null) {
            collider.position = this.position;
            collider.rotation = this.rotation;
            collider.scale = this.scale;
        }
    }

    /**
     * Tells the SceneObject to render itself.
     * @param shader The shader program used to render the SceneObject.
     */
    public void render(HFShader shader) {
        Matrix.setIdentityM(shader.modelViewMatrix, 0);
        Matrix.translateM(shader.modelViewMatrix, 0, position.x, position.y, position.z);
        Matrix.rotateM(shader.modelViewMatrix, 0, rotation.x, 1,0,0);
        Matrix.rotateM(shader.modelViewMatrix, 0, rotation.y, 0,1,0);
        Matrix.rotateM(shader.modelViewMatrix, 0, rotation.z, 0,0,1);
        Matrix.scaleM(shader.modelViewMatrix, 0, scale.x, scale.y, scale.z);

        Matrix.invertM(shader.normalMatrix, 0, shader.modelViewMatrix, 0);
        Matrix.transposeM(shader.normalMatrix, 0, shader.normalMatrix, 0);

        GLES20.glUniformMatrix4fv(shader.getHandle("pMatrix"), 0, false, shader.pMatrix, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("camMatrix"), 0, false, shader.camMatrix, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("mvMatrix"), 0, false, shader.modelViewMatrix, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("normalMatrix"), 0, false, shader.normalMatrix, 0);

        if(vertices != null) {
            vertices.bind(shader);
            if(texture != null) {
                if(texture.isTransparent()) {
                    if(Camera.getMode() == Camera.MODE_3D) {
                        GLES20.glDisable(GLES20.GL_DEPTH_TEST);
                    }
                    GLES20.glEnable(GLES20.GL_BLEND);
                    GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
                }
                texture.activate(shader, GLES20.GL_TEXTURE0);
                vertices.draw();
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
            }
            vertices.unbind(shader);
            if(texture.isTransparent()) {
                GLES20.glDisable(GLES20.GL_BLEND);
            }
            if(Camera.getMode() == Camera.MODE_3D) {
                GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            }
        }
    }

    /**
     * Readies the SceneObject to be removed from the scene.
     */
    public void destroy() {
        Log.d(HFGame.DEBUG_TAG, "Object destroyed");
        markedForDeath = true;
        if(collider != null) {
            collider.setActive(false);
        }
    }

    /**
     * Called when two SceneObjects collide with each other.
     * Override to add extra collision behaviour.
     * @param other The other SceneObject the SceneObject collided with.
     */
    public void onCollide(SceneObject other) {
        Log.d(HFGame.DEBUG_TAG, "Collision Detected");
    }

    /**
     * Sets a new texture for the SceneObject.
     * @param texture The new texture for the SceneObject.
     */
    public void setTexture(HFTexture texture) {
        this.texture = texture;
    }

    /**
     * Sets new collision component for the SceneObject.
     * @param collider The new collision component for the SceneObject.
     */
    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    /**
     *
     * @return The collision component of the SceneObject.
     */
    public Collider getCollider() {
        return collider;
    }

    /**
     * Checks to see if the SceneObejct can be removed from the scene.
     * @return true if the SceneObject can be removed from the scene, else returns false.
     */
    public boolean isMarkedForDeath() {
        return markedForDeath;
    }
}
