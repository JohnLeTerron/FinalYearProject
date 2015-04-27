package com.leterronapps.hyperfour.audio;

import java.io.FileDescriptor;

/**
 * A data class which stores the information required for the playback of music tracks.
 */
public class MusicClip {

    private FileDescriptor descriptor;
    private long startOffset;
    private long length;

    /**
     * Constructs a MusicClip with the required data.
     * @param descriptor the FileDescriptor that refers to the sound file.
     * @param startOffset the starting offset to the begin playing the track at.
     * @param length the length of the music track.
     */
    public MusicClip(FileDescriptor descriptor, long startOffset, long length) {
        this.descriptor = descriptor;
        this.startOffset = startOffset;
        this.length = length;
    }

    /**
     *
     * @return the FileDescriptor for the music clip.
     */
    public FileDescriptor getDescriptor() {
        return descriptor;
    }

    /**
     *
     * @return the starting offset for the music clip.
     */
    public long getStartOffset() {
        return startOffset;
    }

    /**
     *
     * @return the length of the music clip.
     */
    public long getLength() {
        return length;
    }

}
