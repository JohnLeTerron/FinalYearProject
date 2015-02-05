package com.leterronapps.hyperfour.graphics;

import android.opengl.GLES20;

import java.util.HashMap;

/**
 * Created by williamlea on 03/02/15.
 */
public class HFShader {

    private int vertexShader;
    private int fragmentShader;

    private int program;

    private HashMap<String, Integer> handles;

    private final String vertexShaderSrc =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "attribute vex3 vNormal;" +
            "attribute vec2 vTexCoord;" +
            "void main() {" +
            "  gl_Position = uMVPMatrix * vPosition;" +
            "}";

    private final String fragShaderSrc =
            "precision mediump float;" +
            "void main() {" +
            "  gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);" +
            "}";

    public HFShader() {
        vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderSrc);
        fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragShaderSrc);

        handles = new HashMap<>();

        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);

        initHandles();
    }

    private int loadShader(int shaderType, String src) {
        int shader = GLES20.glCreateShader(shaderType);
        GLES20.glShaderSource(shader, src);
        GLES20.glCompileShader(shader);
        return shader;
    }

    private void initHandles() {
        handles.put("position", GLES20.glGetAttribLocation(program, "vPosition"));
        handles.put("normal", GLES20.glGetAttribLocation(program, "vNormal"));
        handles.put("texCoord", GLES20.glGetAttribLocation(program, "vTexCoord"));
        handles.put("matrixHandle", GLES20.glGetUniformLocation(program, "uMVPMatrix"));
    }

    public int getProgram() {
        return program;
    }

    public int getHandle(String handle) {
        return handles.get(handle);
    }
}
