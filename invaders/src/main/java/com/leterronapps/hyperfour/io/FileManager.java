package com.leterronapps.hyperfour.io;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by williamlea on 27/01/15.
 */
public class FileManager {

    private AssetManager assetManager;
    private String externalStoragePath;

    public FileManager(AssetManager assetManager) {
        this.assetManager = assetManager;
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    public InputStream getAsset(String fileName) throws IOException {
        return assetManager.open(fileName);
    }

    public AssetFileDescriptor getAssetFileDescriptor(String fileName) throws IOException {
        return assetManager.openFd(fileName);
    }

    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }

    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }
}
