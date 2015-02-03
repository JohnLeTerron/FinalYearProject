package com.leterronapps.finalyearproject;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;


public class MainActivity extends HFGame {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public HFScene getStartScene() {
        return new TestScene(this);
    }
}
