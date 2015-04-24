package com.leterronapps.hyperfour.audio;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;

import java.io.IOException;

/**
 * Created by williamlea on 28/01/15.
 */
public class SoundManager {

    private HFGame game;

    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    public SoundManager(HFGame activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        mediaPlayer = new MediaPlayer();

        SoundPool.Builder soundBuilder = new SoundPool.Builder();
        soundPool = soundBuilder.build();

        game = activity;
    }

    public MusicClip newMusicClip(String fileName) {
        try {
            AssetFileDescriptor musicDescriptor = game.getFileManager().getAssetFileDescriptor(fileName);
            return new MusicClip(musicDescriptor.getFileDescriptor(), musicDescriptor.getStartOffset(), musicDescriptor.getLength());
        } catch(IOException ex) {
            Log.d(HFGame.DEBUG_TAG, "Failed to load music clip: " + fileName);
            game.finish();

        }
        return null;
    }

    public void loadMusic(MusicClip musicClip) {
        try {
            mediaPlayer.setDataSource(musicClip.getDescriptor(), musicClip.getStartOffset(), musicClip.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        } catch(IOException ex) {
            game.finish();
        }
    }

    public void playMusic() {
        mediaPlayer.start();
    }

    public void pauseMusic() {
        mediaPlayer.pause();
    }

    public void stopMusic() {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    public void releasePlayer() {
        mediaPlayer.release();
    }

    public SoundClip newSoundClip(String fileName) {
        try {
            AssetFileDescriptor soundDescriptor = game.getFileManager().getAssetFileDescriptor(fileName);
            return new SoundClip(soundPool.load(soundDescriptor, 1));
        } catch (IOException ex) {
            Log.d(HFGame.DEBUG_TAG, "Failed to load sound clip: " + fileName);
            game.finish();
        }
        return null;
    }

    public SoundClip newSoundClip(String fileName, int priority) {
        try {
            AssetFileDescriptor soundDescriptor = game.getFileManager().getAssetFileDescriptor(fileName);
            return new SoundClip(soundPool.load(soundDescriptor, 1), priority);
        } catch (IOException ex) {
            Log.d(HFGame.DEBUG_TAG, "Failed to load sound clip: " + fileName);
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
