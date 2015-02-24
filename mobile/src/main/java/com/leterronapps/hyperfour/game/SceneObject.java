package com.leterronapps.hyperfour.game;

import android.opengl.GLES20;
import android.opengl.Matrix;

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

    public SceneObject(Vector3D position) {
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
        //Matrix.rotateM(shader.modelViewMatrix, 0, 1, rotation.x, rotation.y, rotation.z);
        Matrix.scaleM(shader.modelViewMatrix, 0, scale.x, scale.y, scale.z);

        Matrix.invertM(shader.normalMatrix, 0, shader.modelViewMatrix, 0);
        Matrix.transposeM(shader.normalMatrix, 0, shader.normalMatrix, 0);

        GLES20.glUniformMatrix4fv(shader.getHandle("pMatrix"), 0, false, shader.pMatrix, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("camMatrix"), 0, false, shader.camMatrix, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("mvMatrix"), 0, false, shader.modelViewMatrix, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("normalMatrix"), 0, false, shader.normalMatrix, 0);

        vertices.bind(shader);
        if(texture != null) {
            texture.activate(shader, GLES20.GL_TEXTURE0);
        }
        vertices.draw();
        vertices.unbind(shader);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    public void onCollide(SceneObject other) {

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
}
