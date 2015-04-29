package com.leterronapps.hyperfour.util;

import com.leterronapps.hyperfour.game.HFGame;

/**
 * This interface is used for loading custom assets into a game.
 * In the class implementing this interface declare public static fields of the type of asset you desire.
 * For example, for a music clip do the following:<br><br>
 * public static MusicClip myMusicClip;<br><br>
 * Then instanciate it in the load function.
 */
public interface AssetLoader {

    /**
     * Override to load game assets.
     * @param game The game activitty used to assist the loading process.
     */
    public void load(HFGame game);
}
