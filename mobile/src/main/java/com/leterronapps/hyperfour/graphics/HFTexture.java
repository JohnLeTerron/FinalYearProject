package com.leterronapps.hyperfour.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import com.leterronapps.hyperfour.game.HFGame;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by williamlea on 10/02/15.
 */
public class HFTexture {

    private int width;
    private int height;

    private int textureId;

    private HFGame game;
    private String textureFile;

    public HFTexture(HFGame game, String textureFile) {
        this.game = game;
        this.textureFile = textureFile;
        //load();
    }

    public void load() {
        int[] texIds = new int[1];
        GLES20.glGenTextures(1, texIds, 0);
        textureId = texIds[0];

        InputStream in;
        try {
            in = game.getFileManager().getAsset(textureFile);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            width = bitmap.getWidth();
            height = bitmap.getHeight();
            bind();
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
            bitmap.recycle();
        } catch(IOException ex) {

        }
    }

    public void bind() {
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
    }

    public void activate(HFShader shader, int texture) {
        GLES20.glActiveTexture(texture);
        bind();
        GLES20.glUniform1i(shader.getHandle("sampler0"), 0);
    }

    public void delete() {
        bind();
        int[] textureIds = {textureId};
        GLES20.glDeleteTextures(1, textureIds, 0);
    }

}
