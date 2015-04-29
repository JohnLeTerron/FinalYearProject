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
 * The File manager for the HyperFour engine. This class is the interface for file management.
 */
public class FileManager {

    private AssetManager assetManager;
    private String externalStoragePath;

    /**
     * Constructs the FileManger.
     * @param assetManager The AssetManger from the HFGame activity.
     */
    public FileManager(AssetManager assetManager) {
        this.assetManager = assetManager;
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * Retrieves an asset file from the app's asset directory.
     * @param fileName The name of the asset you want to open.
     * @return An InputStream for the requested asset.
     * @throws IOException if the asset wasn't found.
     */
    public InputStream getAsset(String fileName) throws IOException {
        return assetManager.open(fileName);
    }

    /**
     * Retrievs an asset file in the form its file descriptor. This the method is for loading audio assets.
     * @param fileName The name of the asset you want to open.
     * @return An AssetFileDescriptor for the asset file you want to open.
     * @throws IOException if the asset wasn't found.
     */
    public AssetFileDescriptor getAssetFileDescriptor(String fileName) throws IOException {
        return assetManager.openFd(fileName);
    }

    /**
     * Opens a non-asset file for reading from the app's external storage directory.
     * @param fileName The name of the asset you want to open and read.
     * @return An InputStream for the requested file.
     * @throws IOException if the file wasn't found.
     */
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }

    /**
     * Opens a non-asset file for writing to from the app's external storage directory.
     * @param fileName The name of the asset you want to open and write to.
     * @return An OutputStream for the requested file.
     * @throws IOException if the file wasn't found.
     */
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }
}
