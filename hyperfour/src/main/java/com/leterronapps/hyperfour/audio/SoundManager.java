package com.leterronapps.hyperfour.audio;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;

import java.io.IOException;

/**
 * The Sound manager for the HyperFour engine. This class is the interface for audio playback.
 */
public class SoundManager {

    private HFGame game;

    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    /**
     * Constructs the SoundManager.
     * @param activity The link back to the game activity used to set up the app to play audio.
     */
    public SoundManager(HFGame activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        mediaPlayer = new MediaPlayer();

        SoundPool.Builder soundBuilder = new SoundPool.Builder();
        soundPool = soundBuilder.build();

        game = activity;
    }

    /**
     * Creates a new MusicClip object ready for playback.
     * @param fileName The name of the music file.
     * @return A new MusicClip or null if the file wasn't found.
     */
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

    /**
     * Loads a MusicClip into the MediaPlayer ready for playback.
     * @param musicClip The MusicClip to be loaded.
     */
    public void loadMusic(MusicClip musicClip) {
        try {
            mediaPlayer.setDataSource(musicClip.getDescriptor(), musicClip.getStartOffset(), musicClip.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        } catch(IOException ex) {
            game.finish();
        }
    }

    /**
     * Tells the MediaPlayer to play the current MusicClip loaded into it.
     */
    public void playMusic() {
        mediaPlayer.start();
    }

    /**
     * Tells the MediaPlayer to pause the currently playing MusicClip.
     */
    public void pauseMusic() {
        mediaPlayer.pause();
    }

    /**
     * Tells the MediaPlayer to stop the currently playing MusicClip and reset itself ready to accept another MusicClip.
     */
    public void stopMusic() {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    /**
     * Releases the MediaPlayer from the game.
     */
    public void releasePlayer() {
        mediaPlayer.release();
    }

    /**
     * Creates a new SoundClip object ready for playback.
     * @param fileName The name of the sound file.
     * @return A new SoundClip or null if the file wasn't found.
     */
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

    /**
     * Creates a new SoundClip object ready for playback with a custom priority.
     * @param fileName The name of the sound file.
     * @param priority The priority of the SoundClip.
     * @return A new SoundClip or null if the file wasn't found.
     */
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

    /**
     * Tells the SoundPool to play a SoundClip.
     * @param soundClip The SoundClip to be played.
     */
    public void playSound(SoundClip soundClip) {
        soundPool.play(soundClip.getId(), 1, 1, soundClip.getPriority(), 0, 1);
    }

    /**
     * Tells the SoundPool to pause a SoundClip.
     * @param soundClip The SoundClip to be paused.
     */
    public void pauseSound(SoundClip soundClip) {
        soundPool.pause(soundClip.getId());
    }

    /**
     * Tells the SoundPool to stop playing a SounClip
     * @param soundClip The SoundClip to be stopped.
     */
    public void stopSound(SoundClip soundClip) {
        soundPool.stop(soundClip.getId());
    }
}
