package com.leterronapps.finalyearproject;

import android.os.Bundle;
import android.util.Log;

import com.leterronapps.hyperfour.HFGame;

import java.io.IOException;


public class MainActivity extends HFGame {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            fileManager.getAsset("scifi_final.png");
            Log.d(DEBUG_TAG, "Asset Loaded");
        }catch (IOException ex) {
            Log.d(DEBUG_TAG, "Failed to Load Asset - Closing Application");
            finish();
        }
    }
}
