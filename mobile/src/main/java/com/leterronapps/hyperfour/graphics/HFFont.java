package com.leterronapps.hyperfour.graphics;

import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.util.Vector3D;

import java.util.Vector;

/**
 * Created by williamlea on 10/03/15.
 */
public class HFFont {

    private HFSubTexture[] glyphs = new HFSubTexture[96];
    private int glyphWidth;
    private int glyphHeight;
    private HFTexture fontSheet;

    public HFFont(HFTexture fontSheet, int glyphsPerRow, int glyphWidth, int glyphHeight) {
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;
        this.fontSheet = fontSheet;

        int x = 0;
        int y = 0;

        for(int i = 0; i < 96; i++) {
            glyphs[i] = new HFSubTexture(fontSheet, x, y, glyphWidth, glyphHeight);
            x += glyphWidth;
            if(x == glyphsPerRow * glyphWidth) {
                x = 0;
                y += glyphHeight;
            }
        }
    }

    public Vector<Sprite> makeText(String text, float size, float x, float y) {
        Vector<Sprite> result = new Vector<>(text.length());
        for (int i = 0; i < text.length(); i++) {
            int character = text.charAt(i) - 32;
            if(character < 0 || character > glyphs.length - 1) {
                continue;
            }
            result.add(new Sprite(new Vector3D(x, y, 0), size, size));
            result.get(i).setTexture(fontSheet);
            result.get(i).setSubTexture(glyphs[character]);
            x += size / 2;
        }
        return result;
    }
}
