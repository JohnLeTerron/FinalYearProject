package com.leterronapps.hyperfour.game;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.leterronapps.hyperfour.graphics.HFShader;
import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 05/02/15.
 */
public abstract class SceneObject {

    public Vector3D position;
    public Vector3D rotation;
    public Vector3D scale;

    protected Vertices vertices;

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

    public abstract void update(float deltaTime);

    public void render(HFShader shader) {
        Matrix.setIdentityM(shader.modelViewMatrix, 0);
        Matrix.translateM(shader.modelViewMatrix, 0, position.x, position.y, position.z);

        GLES20.glUniformMatrix4fv(shader.getHandle("pMatrix"), 0, false, shader.pMatrix, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("camMatrix"), 0, false, shader.camMatrix, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("mvMatrix"), 0, false, shader.modelViewMatrix, 0);

        vertices.bind(shader);
        vertices.draw();
        vertices.unbind(shader);
    }
}
