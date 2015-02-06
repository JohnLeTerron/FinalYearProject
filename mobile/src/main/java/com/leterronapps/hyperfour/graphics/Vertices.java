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
    private FloatBuffer normalBuffer;
    private FloatBuffer texCoordBuffer;
    private ShortBuffer indexBuffer;

    private final int VERTEX_COUNT;
    private final int VERTEX_STRIDE;
    private final int VERTEX_SIZE = 3;

    private final int NORMAL_STRIDE;
    private final int NORMAL_SIZE = 3;

    private final int TEXTURE_COORD_STRIDE;
    private final int TEXTURE_COORD_SIZE = 2;

    private HFShader shader;

    public Vertices(float[] vertices, float[] normals, float[] texCoords) {
        VERTEX_STRIDE = VERTEX_SIZE * 4;
        NORMAL_STRIDE = NORMAL_SIZE * 3;
        TEXTURE_COORD_STRIDE = TEXTURE_COORD_SIZE * 4;
        VERTEX_COUNT = vertices.length;

        ByteBuffer bb = ByteBuffer.allocateDirect(VERTEX_COUNT * VERTEX_STRIDE);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        bb = ByteBuffer.allocateDirect(normals.length * NORMAL_STRIDE);
        bb.order(ByteOrder.nativeOrder());
        normalBuffer = bb.asFloatBuffer();
        normalBuffer.put(normals);
        normalBuffer.position(0);

        bb = ByteBuffer.allocateDirect(texCoords.length * TEXTURE_COORD_STRIDE);
        bb.order(ByteOrder.nativeOrder());
        texCoordBuffer = bb.asFloatBuffer();
        texCoordBuffer.put(texCoords);
        texCoordBuffer.position(0);
    }

    public void bind() {
        int positionHandle = shader.getHandle("position");
        int normalHandle = shader.getHandle("normal");
        int texCoordHandle = shader.getHandle("texCoord");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(
                positionHandle, VERTEX_SIZE,
                GLES20.GL_FLOAT, false,
                VERTEX_STRIDE, vertexBuffer);
        GLES20.glEnableVertexAttribArray(normalHandle);
        GLES20.glVertexAttribPointer(
                normalHandle, NORMAL_SIZE,
                GLES20.GL_FLOAT, false,
                NORMAL_STRIDE, normalBuffer);
        GLES20.glEnableVertexAttribArray(texCoordHandle);
        GLES20.glVertexAttribPointer(
                texCoordHandle, TEXTURE_COORD_SIZE,
                GLES20.GL_FLOAT, false,
                TEXTURE_COORD_STRIDE, vertexBuffer);
    }

    public void unbind() {
        int positionHandle = shader.getHandle("position");
        int normalHandle = shader.getHandle("normal");
        int texCoordHandle = shader.getHandle("texCoord");
        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(normalHandle);
        GLES20.glDisableVertexAttribArray(texCoordHandle);
    }

    public void draw() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, VERTEX_COUNT);
    }

    public void setShader(HFShader shader) {
        this.shader = shader;
    }

}
