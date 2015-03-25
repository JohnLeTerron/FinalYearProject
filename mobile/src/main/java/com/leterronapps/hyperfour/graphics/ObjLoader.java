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
        InputStream in = null;
        try {
            in = game.getFileManager().getAsset(fileName);
            Vector<String> lines = readLines(in);

            float[] verts = new float[lines.size() * 3];
            float[] norms = new float[lines.size() * 3];
            float[] textCoords = new float[lines.size() * 2];

            int numVerts = 0;
            int numNorms = 0;
            int numTexCoords = 0;
            int numFaces = 0;

            int vertIndex = 0;
            int normIndex = 0;
            int texIndex = 0;
            int faceIndex = 0;

            for(String line : lines) {
                if(line.startsWith("v ")) {
                    String[] tokens = line.split("[ ]+");
                    verts[vertIndex] = Float.parseFloat(tokens[1]);
                    verts[vertIndex + 1] = Float.parseFloat(tokens[2]);
                    verts[vertIndex + 2] = Float.parseFloat(tokens[3]);
                    vertIndex += 3;
                    numVerts++;
                    continue;
                }
                if(line.startsWith("vn ")) {
                    String[] tokens = line.split("[ ]+");
                    norms[normIndex] = Float.parseFloat(tokens[1]);
                    norms[normIndex + 1] = Float.parseFloat(tokens[2]);
                    norms[normIndex + 2] = Float.parseFloat(tokens[3]);
                    normIndex += 3;
                    numNorms++;
                    continue;
                }
                if(line.startsWith("vt ")) {
                    String[] tokens = line.split("[ ]+");
                    textCoords[texIndex] = Float.parseFloat(tokens[1]);
                    textCoords[texIndex + 1] = Float.parseFloat(tokens[2]);
                    texIndex += 2;
                    numTexCoords++;
                    continue;
                }
                if(line.startsWith("f ")) {
                    String[] tokens = line.split("[ ] +");
                    
                }
            }

        } catch(IOException e) {}
    }

    private static Vector<String> readLines(InputStream in) throws IOException {
        Vector<String> result = new Vector<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while((line = reader.readLine()) != null) {
            result.add(line);
        }
        return result;
    }
}
