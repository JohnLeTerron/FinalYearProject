package com.leterronapps.hyperfour.graphics;

import com.leterronapps.hyperfour.game.Sprite;

import java.util.Vector;

/**
 * Created by williamlea on 09/03/15.
 */
public class SpriteAnimation {

    private Sprite owner;
    private Vector<HFSubTexture> frames;

    private float duration;
    private int numFrames;
    private float frameTick;
    private int currentFrame;

    private boolean looping = true;
    private boolean finished = false;

    public SpriteAnimation(Sprite owner, int numFrames, float duration) {
        this.owner = owner;
        this.duration = duration;
        this.frames = new Vector<>();
        this.currentFrame = 0;
        this.numFrames = numFrames;
    }

    public void setup(HFTexture spriteSheet) {
        int spriteWidth = spriteSheet.getWidth() / numFrames;
        int offsetX = 0;

        for(int i = 0; i < numFrames; i++) {
            frames.add(new HFSubTexture(spriteSheet, offsetX, 0, spriteWidth, spriteSheet.getHeight()));
            offsetX += spriteWidth;
        }
        owner.setSubTexture(frames.get(0));
    }

    public void play(float deltaTime) {
        float frameTime = duration / (float) numFrames;
        frameTick += deltaTime;
        if(frameTick > frameTime) {
            if(currentFrame < numFrames - 1) {
                currentFrame++;
            } else if(looping) {
                currentFrame = 0;
            } else {
                finished = true;
            }
            owner.setSubTexture(frames.get(currentFrame));
            frameTick = 0;
        }
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public boolean isLooping() {
        return looping;
    }

    public boolean isFinished() {
        return finished;
    }
}
