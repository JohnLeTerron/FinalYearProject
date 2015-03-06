package com.leterronapps.hyperfour.graphics;

import android.opengl.GLES20;

import java.util.HashMap;

/**
 * Created by williamlea on 03/02/15.
 */
public class HFShader {

    private int program;

    private HashMap<String, Integer> handles;

    private final String vertexShaderSrc =
            "uniform mat4 uPMatrix;" +
            "uniform mat4 uCamMatrix;" +
            "uniform mat4 uMVMatrix;" +
            "uniform mat4 uNormalMatrix;" +
            "attribute vec4 aPosition;" +
            "attribute vec3 aNormal;" +
            "attribute vec2 aTexCoord;" +
            "varying vec2 vTexCoord;" +
            "varying vec3 vTransNormal;" +
            "varying vec4 vPosition;" +
            "void main() {" +
            "  vTexCoord = aTexCoord;" +
            "  vTransNormal = (uNormalMatrix * vec4(aNormal, 1.0)).xyz;" +
            "  vPosition = uMVMatrix * aPosition;" +
            "  gl_Position = uPMatrix * uCamMatrix * vPosition;" +
            "}";

    private final String fragShaderSrc =
            "precision mediump float;" +
            "uniform sampler2D uSampler;" +
            "uniform vec3 uPointLightPos;" +
            "varying vec2 vTexCoord;" +
            "varying vec3 vTransNormal;" +
            "varying vec4 vPosition;" +
            "void main() {" +
            "  vec4 textureColour = texture2D(uSampler, vTexCoord);" +
            "  vec3 pointLightPos = vec3(0.0, 10.0, -3.0);" +
            "  vec3 s = normalize(uPointLightPos - vPosition.xyz);" +
            "  vec3 n = normalize(vTransNormal);" +
            "  vec3 v = normalize(-vPosition.xyz);" +
            "  vec3 r = reflect(-s, vTransNormal);" +
            "  float SdotN = max(dot(s,n), 0.0);" +
            "  vec3 ambient = vec3(0.8, 0.8, 0.8);" +
            "  vec3 diffuse = vec3(0.5, 0.5, 0.0) * SdotN;" +
            "  vec3 specular = vec3(0.0, 0.0, 0.0);" +
            "  if(SdotN > 0.0) {" +
            "       specular = vec3(0.0, 0.0, 0.0) * pow(max(dot(r, v), 0.0), 25.0);" +
            "  }" +
            "  vec3 lightIntensity = ambient + diffuse + specular;" +
            "  gl_FragColor = vec4(textureColour.rgb * lightIntensity, textureColour.a);" +
            "}";

    public final float[] pMatrix = new float[16];
    public final float[] camMatrix = new float[16];
    public final float[] modelViewMatrix = new float[16];
    public final float[] normalMatrix = new float[16];

    public HFShader() {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderSrc);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragShaderSrc);

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
        handles.put("position", GLES20.glGetAttribLocation(program, "aPosition"));
        handles.put("normal", GLES20.glGetAttribLocation(program, "aNormal"));
        handles.put("texCoord", GLES20.glGetAttribLocation(program, "aTexCoord"));
        handles.put("pMatrix", GLES20.glGetUniformLocation(program, "uPMatrix"));
        handles.put("camMatrix", GLES20.glGetUniformLocation(program, "uCamMatrix"));
        handles.put("mvMatrix", GLES20.glGetUniformLocation(program, "uMVMatrix"));
        handles.put("normalMatrix", GLES20.glGetUniformLocation(program, "uNormalMatrix"));
        handles.put("sampler0", GLES20.glGetUniformLocation(program, "uSampler"));
        handles.put("pointLightPos", GLES20.glGetUniformLocation(program, "uPointLightPos"));
    }

    public int getProgram() {
        return program;
    }

    public int getHandle(String handle) {
        return handles.get(handle);
    }
}
