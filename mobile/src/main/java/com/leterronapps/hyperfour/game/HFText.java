package com.leterronapps.hyperfour.game;

import com.leterronapps.hyperfour.graphics.HFSubTexture;
import com.leterronapps.hyperfour.graphics.HFTexture;

import java.util.Vector;

/**
 * Created by williamlea on 03/03/15.
 */
public class HFText {

    private HFTexture charSheet;
    private Vector<HFSubTexture> characters;

    private String text;

    public HFText(HFTexture charSheet, String text) {
        this.charSheet = charSheet;
        this.text = text;
    }
}
