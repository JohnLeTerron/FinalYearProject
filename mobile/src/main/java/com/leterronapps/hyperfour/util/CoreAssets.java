package com.leterronapps.hyperfour.util;

import com.leterronapps.hyperfour.audio.MusicClip;
import com.leterronapps.hyperfour.audio.SoundClip;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.util.AssetLoader;

/**
 * Created by williamlea on 02/02/15.
 */
public class CoreAssets implements AssetLoader {

    public static MusicClip bgMusic;
    public static SoundClip tickSound;

    @Override
    public void load(HFGame game) {
        bgMusic = game.getSoundManager().newMusicClip("pitch_black.mp3");
        tickSound = game.getSoundManager().newSoundClip("tick.mp3");
    }
}
