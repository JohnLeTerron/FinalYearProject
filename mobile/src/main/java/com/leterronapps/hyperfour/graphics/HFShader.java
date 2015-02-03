package com.leterronapps.hyperfour.graphics;

import android.opengl.GLES20;

/**
 * Created by williamlea on 03/02/15.
 */
public class HFShader {

    private int vertexShader;
    private int fragmentShader;

    private int program;

    public HFShader(String vertex, String fragment) {
        vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertex);
        fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragment);

        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
    }

    private int loadShader(int shaderType, String src) {
        int shader = GLES20.glCreateShader(shaderType);
        GLES20.glShaderSource(shader, src);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public int getProgram() {
        return program;
    }
}
