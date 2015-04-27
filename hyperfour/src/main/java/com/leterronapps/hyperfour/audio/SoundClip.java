package com.leterronapps.hyperfour.audio;

/**
 * A data class which stores the information required for the playback of short sounds.
 */
public class SoundClip {

    private int id = -1;
    private int priority = 0;

    /**
     * Constructs a SoundClip with an ID.
     * @param id the ID of the sound clip.
     */
    public SoundClip(int id) {
        this.id = id;
    }

    /**
     * Constructs a SoundClip with an ID and a priority.
     * @param id the ID of the sound clip.
     * @param priority the priority of the sound clip.
     */
    public SoundClip(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    /**
     *
     * @return the ID of the sound clip.
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return the priority of the sound clip.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets a new priority for the sound clip.
     * @param priority the new priority of the sound clip.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

}
