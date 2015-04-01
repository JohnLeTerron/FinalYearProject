package com.leterronapps.hyperfour.graphics;

import com.leterronapps.hyperfour.game.HFGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created by williamlea on 25/03/15.
 */
public class ObjLoader {

    public static void load(HFGame game, String fileName) {
        InputStream in;
        try {
            in = game.getFileManager().getAsset(fileName);
            Vector<String> lines = readLines(in);

            Vector<Float> vertices = new Vector<>();
            Vector<Float> normals = new Vector<>();
            Vector<Float> texCoords = new Vector<>();

            for(String line : lines) {
                if(line.startsWith("v ")) {
                    String[] tokens = line.split("[ ]+");
                    vertices.add(Float.parseFloat(tokens[1]));
                    vertices.add(Float.parseFloat(tokens[2]));
                    vertices.add(Float.parseFloat(tokens[3]));
                } else if(line.startsWith("vn ")) {
                    String[] tokens = line.split("[ ]+");
                    normals.add(Float.parseFloat(tokens[1]));
                    normals.add(Float.parseFloat(tokens[2]));
                    normals.add(Float.parseFloat(tokens[3]));
                }else if(line.startsWith("vt ")) {
                    String[] tokens = line.split("[ ]+");
                    texCoords.add(Float.parseFloat(tokens[1]));
                    texCoords.add(Float.parseFloat(tokens[2]));
                }else if(line.startsWith("f ")) {

                }
            }

        } catch(IOException e) {}
    }

    private static Vector<String> readLines(InputStream in) throws IOException {
        Vector<String> result = new Vector<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while((line = reader.readLine()) != null) {
            result.add(line);
        }
        return result;
    }

    private static int getIndex(String index, int size) {
        int id = Integer.parseInt(index);
        if(id < 0) {
            return size + id;
        } else {
            return id - 1;
        }
    }
}
