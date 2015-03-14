package com.leterronapps.hyperfour.graphics;

import com.leterronapps.hyperfour.game.Sprite;

import java.util.Vector;

/**
 * Created by williamlea on 14/03/15.
 */
public class HFString {

    private HFFont font;

    private Vector<Sprite> text;

    public HFString(HFFont font, String text) {
        this.font = font;
        this.text = new Vector<>();
        //this.text = font.makeText();
    }

    public void setText(String text) {
        this.text = font.setText(this.text, text);
    }
}
