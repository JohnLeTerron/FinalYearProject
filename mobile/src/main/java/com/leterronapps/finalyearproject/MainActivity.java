package com.leterronapps.finalyearproject;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.leterronapps.hyperfour.HFGame;
import com.leterronapps.hyperfour.MusicClip;


public class MainActivity extends HFGame {

    MusicClip bgMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        bgMusic = soundManager.newMusicClip("pitch_black.mp3");
        soundManager.loadMusic(bgMusic);
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundManager.pauseMusic();
        if(isFinishing()) {
            soundManager.stopMusic();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        soundManager.playMusic();
    }
}
