package com.leterronapps.hyperfour;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

/**
 * Created by williamlea on 28/01/15.
 */
public class SoundManager {

    private HFGame game;

    private SoundPool soundPool;

    public SoundManager(HFGame activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        SoundPool.Builder soundBuilder = new SoundPool.Builder();

        soundPool = soundBuilder.build();
        game = activity;
    }

    public SoundClip newSoundClip(String fileName) {
        try {
            AssetFileDescriptor soundDescriptor = game.fileManager.getAssetFileDescriptor(fileName);
            return new SoundClip(soundPool.load(soundDescriptor, 1));
        } catch (IOException ex) {
            Log.d(game.DEBUG_TAG, "Failed to load sound clip: " + fileName);
            game.finish();
        }
        return null;
    }

    public SoundClip newSoundClip(String fileName, int priority) {
        try {
            AssetFileDescriptor soundDescriptor = game.fileManager.getAssetFileDescriptor(fileName);
            return new SoundClip(soundPool.load(soundDescriptor, 1), priority);
        } catch (IOException ex) {
            Log.d(game.DEBUG_TAG, "Failed to load sound clip: " + fileName);
            game.finish();
        }
        return null;
    }

    public void playSound(SoundClip soundClip) {
        soundPool.play(soundClip.getId(), 1, 1, soundClip.getPriority(), 0, 1);
    }

    public void pauseSound(SoundClip soundClip) {
        soundPool.pause(soundClip.getId());
    }

    public void stopSound(SoundClip soundClip) {
        soundPool.stop(soundClip.getId());
    }
}
