package com.leterronapps.hyperfour;

/**
 * Created by williamlea on 28/01/15.
 */
public class SoundClip {

    private int id = -1;
    private int priority = 0;
    private int looping = 0;

    public SoundClip(int id) {
        this.id = id;
    }

    public SoundClip(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public SoundClip(int id, int priority, boolean looping) {
        this.id = id;
        this.priority = priority;
        if(looping) {
            this.looping = -1;
        }
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isLooping() {
        return looping == -1;
    }

    public void setLooping(boolean looping) {
        if(looping) {
            this.looping = -1;
        } else {
            this.looping = 0;
        }
    }
}
