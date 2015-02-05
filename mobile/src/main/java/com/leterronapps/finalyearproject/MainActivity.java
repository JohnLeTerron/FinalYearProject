package com.leterronapps.finalyearproject;


import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;


public class MainActivity extends HFGame {


    @Override
    public HFScene getStartScene() {
        return new TestScene(this);
    }
}
