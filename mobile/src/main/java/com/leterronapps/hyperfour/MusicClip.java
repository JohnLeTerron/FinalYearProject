package com.leterronapps.hyperfour;

import java.io.FileDescriptor;

/**
 * Created by williamlea on 28/01/15.
 */
public class MusicClip {

    private FileDescriptor descriptor;
    private long startOffset;
    private long length;

    public MusicClip(FileDescriptor descriptor, long startOffset, long length) {
        this.descriptor = descriptor;
        this.startOffset = startOffset;
        this.length = length;
    }

    public FileDescriptor getDescriptor() {
        return descriptor;
    }

    public long getStartOffset() {
        return startOffset;
    }

    public long getLength() {
        return length;
    }

}
