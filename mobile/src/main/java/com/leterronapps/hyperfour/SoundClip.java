package com.leterronapps.hyperfour;

/**
 * Created by williamlea on 28/01/15.
 */
public class SoundClip {

    private int id = -1;
    private int priority = 0;

    public SoundClip(int id) {
        this.id = id;
    }

    public SoundClip(int id, int priority) {
        this.id = id;
        this.priority = priority;
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

}
