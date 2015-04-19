package com.leterronapps.invaders;

import android.os.Bundle;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;

/**
 * Created by williamlea on 19/04/15.
 */
public class MainActivity extends HFGame {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameAssets = new InvaderAssets();
    }

    @Override
    public HFScene getStartScene() {
        return new InvaderStartScene(this);
    }
}
