package com.leterronapps.hyperfour.graphics;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by williamlea on 04/02/15.
 */
public class Vertices {

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;

    private final int VERTEX_COUNT;
    private final int VERTEX_STRIDE;
    private final int VERTEX_SIZE = 3;

    private final int NORMAL_SIZE = 3;
    private final int TEXTURE_COORD_SIZE = 2;

    private HFShader shader;

    private boolean isTextured;

    public Vertices(float[] vertices, int numVertices, boolean textured) {
        VERTEX_STRIDE = (VERTEX_SIZE + (textured ? TEXTURE_COORD_SIZE : 0)) * 4;
        VERTEX_COUNT = numVertices;
        shader = new HFShader();
        isTextured = textured;

        ByteBuffer bb = ByteBuffer.allocateDirect(numVertices * VERTEX_STRIDE);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }

    public void bind() {
        int positionHandle = shader.getHandle("position");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(
                positionHandle, VERTEX_SIZE,
                GLES20.GL_FLOAT, false,
                VERTEX_STRIDE, vertexBuffer);

    }

    public void unbind() {
        int positionHandle = shader.getHandle("position");
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    public void draw() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, VERTEX_COUNT);
    }

    public void setShader(HFShader shader) {
        this.shader = shader;
    }
}
