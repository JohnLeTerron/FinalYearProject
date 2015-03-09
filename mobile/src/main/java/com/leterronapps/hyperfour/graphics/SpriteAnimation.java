package com.leterronapps.hyperfour.graphics;

import java.util.Vector;

/**
 * Created by williamlea on 09/03/15.
 */
public class SpriteAnimation {

    private float duration;

    private Vector<HFSubTexture> frames;

    public SpriteAnimation(HFTexture spriteSheet, int numFrames, int duration) {
        this.duration = duration;
        this.frames = new Vector<>();


    }
}
