package com.leterronapps.hyperfour.graphics;

import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.util.Vector2D;

import java.util.Vector;

/**
 * Created by williamlea on 14/03/15.
 */
public class HFString {

    private Vector2D position;

    private HFFont font;

    private Vector<Sprite> text;

    public HFString(HFFont font, Vector2D position, String text) {
        this.font = font;
        this.position = position;
        this.text = new Vector<>();
        this.text = font.makeText(text, 20, position.x, position.y);
    }

    public void render(HFShader shader) {
        for(Sprite s : text) {
           s.render(shader);
        }
    }

    public void setText(String text) {
        this.text = font.setText(this.text, text);
    }
}
