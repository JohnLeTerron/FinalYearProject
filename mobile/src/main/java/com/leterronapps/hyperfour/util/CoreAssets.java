package com.leterronapps.hyperfour.util;

import com.leterronapps.hyperfour.audio.MusicClip;
import com.leterronapps.hyperfour.audio.SoundClip;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFTexture;
import com.leterronapps.hyperfour.util.AssetLoader;

/**
 * Created by williamlea on 02/02/15.
 */
public class CoreAssets implements AssetLoader {

    public static MusicClip bgMusic;
    public static SoundClip tickSound;

    public static HFTexture scifiPanel;
    public static HFTexture font;

    @Override
    public void load(HFGame game) {
        bgMusic = game.getSoundManager().newMusicClip("pitch_black.mp3");
        tickSound = game.getSoundManager().newSoundClip("tick.mp3");
        scifiPanel = new HFTexture(game, "scifi_final.png", false);
        scifiPanel.load();
        font = new HFTexture(game, "font.png", true);
        font.load();
    }
}
