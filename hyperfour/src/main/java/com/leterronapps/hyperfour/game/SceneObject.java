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
 * Created by williamlea on 05/02/15.
 */
public abstract class SceneObject {

    public Vector3D position;
    public Vector3D rotation;
    public Vector3D scale;

    protected Vertices vertices;
    protected HFTexture texture;
    protected Collider collider;

    protected HFScene scene;

    private boolean markedForDeath = false;

    public SceneObject() {
        position = new Vector3D();
        rotation = new Vector3D();
        scale = new Vector3D(1.0f, 1.0f, 1.0f);
    }

    public SceneObject(Vector3D position) {
        this.position = position;
        rotation = new Vector3D();
        scale = new Vector3D(1.0f, 1.0f, 1.0f);
    }

    public SceneObject(HFScene scene, Vector3D position) {
        this.scene = scene;
        this.position = position;
        rotation = new Vector3D();
        scale = new Vector3D(1.0f, 1.0f, 1.0f);
    }

    public void update(float deltaTime) {
        if(collider != null) {
            collider.position = this.position;
            collider.rotation = this.rotation;
            collider.scale = this.scale;
        }
    }

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

    public void destroy() {
        Log.d(HFGame.DEBUG_TAG, "Object destroyed");
        markedForDeath = true;
        if(collider != null) {
            collider.setActive(false);
        }
    }

    public void onCollide(SceneObject other) {
        Log.d(HFGame.DEBUG_TAG, "Collision Detected");
    }

    public void setTexture(HFTexture texture) {
        this.texture = texture;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    public Collider getCollider() {
        return collider;
    }

    public boolean isMarkedForDeath() {
        return markedForDeath;
    }
}
