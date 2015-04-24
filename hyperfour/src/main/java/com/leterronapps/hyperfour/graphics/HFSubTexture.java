package com.leterronapps.hyperfour.graphics;

/**
 * Created by williamlea on 03/03/15.
 */
public class HFSubTexture {

    public float u1;
    public float v1;
    public float u2;
    public float v2;

    public HFSubTexture(HFTexture texture, float x, float y, float width, float height) {
        u1 = x / texture.getWidth();
        v1 = y / texture.getHeight();
        u2 = u1 + width / texture.getWidth();
        v2 = v1 + height / texture.getHeight();
    }
}
