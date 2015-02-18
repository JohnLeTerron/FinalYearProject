package com.leterronapps.hyperfour.game.game2d;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.leterronapps.hyperfour.graphics.HFShader;
import com.leterronapps.hyperfour.graphics.HFTexture;
import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.Collider2D;
import com.leterronapps.hyperfour.util.Vector2D;

/**
 * Created by williamlea on 18/02/15.
 */
public class SceneObject2D {

    public Vector2D position;
    public Vector2D rotation;
    public Vector2D scale;

    protected Vertices vertices;
    protected HFTexture texture;
    protected Collider2D collider;

    public SceneObject2D(Vector2D position) {
        this.position = position;
        rotation = new Vector2D();
        scale = new Vector2D(1.0f, 1.0f);
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
        Matrix.translateM(shader.modelViewMatrix, 0, position.x, position.y, 0f);
        //Matrix.rotateM(shader.modelViewMatrix, 0, 1, rotation.x, rotation.y, rotation.z);
        Matrix.scaleM(shader.modelViewMatrix, 0, scale.x, scale.y, 1f);

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

    public void setTexture(HFTexture texture) {
        this.texture = texture;
    }

    public void setCollider(Collider2D collider) {
        this.collider = collider;
    }
}
