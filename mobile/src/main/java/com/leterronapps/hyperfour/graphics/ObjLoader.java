package com.leterronapps.hyperfour.graphics;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.util.Vector2D;
import com.leterronapps.hyperfour.util.Vector3D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created by williamlea on 25/03/15.
 */
public class ObjLoader {

    public static Vertices load(HFGame game, String fileName) {
        InputStream in;
        try {
            in = game.getFileManager().getAsset(fileName);
            Vector<String> lines = readLines(in);

            Vector<Vector3D> vertices = new Vector<>();
            Vector<Vector3D> normals = new Vector<>();
            Vector<Vector2D> texCoords = new Vector<>();

            Vector<Float> resultVerts = new Vector<>();
            Vector<Float> resultNorms = new Vector<>();
            Vector<Float> resultTexCoords = new Vector<>();

            for(String line : lines) {
                if(line.startsWith("v ")) {
                    String[] tokens = line.split("[ ]+");
                    Vector3D vertex = new Vector3D(Float.parseFloat(tokens[1]),
                                                   Float.parseFloat(tokens[2]),
                                                   Float.parseFloat(tokens[3]));
                    vertices.add(vertex);
                } else if(line.startsWith("vn ")) {
                    String[] tokens = line.split("[ ]+");
                    Vector3D normal = new Vector3D(Float.parseFloat(tokens[1]),
                                                   Float.parseFloat(tokens[2]),
                                                   Float.parseFloat(tokens[3]));
                    normals.add(normal);
                }else if(line.startsWith("vt ")) {
                    String[] tokens = line.split("[ ]+");
                    Vector2D texCoord = new Vector2D(Float.parseFloat(tokens[1]),
                                                     Float.parseFloat(tokens[2]));
                    texCoords.add(texCoord);
                }else if(line.startsWith("f ")) {
                    String[] tokens = line.split("[ ]+");

                    String[] parts = tokens[1].split("/");
                    resultVerts.add(vertices.get(Integer.parseInt(parts[0]) - 1).x);
                    resultVerts.add(vertices.get(Integer.parseInt(parts[0]) - 1).y);
                    resultVerts.add(vertices.get(Integer.parseInt(parts[0]) - 1).z);

                    resultNorms.add(normals.get(Integer.parseInt(parts[2]) - 1).x);
                    resultNorms.add(normals.get(Integer.parseInt(parts[2]) - 1).y);
                    resultNorms.add(normals.get(Integer.parseInt(parts[2]) - 1).z);

                    resultTexCoords.add(texCoords.get(Integer.parseInt(parts[1]) - 1).x);
                    resultTexCoords.add(texCoords.get(Integer.parseInt(parts[1]) - 1).y);

                    parts = tokens[2].split("/");
                    resultVerts.add(vertices.get(Integer.parseInt(parts[0]) - 1).x);
                    resultVerts.add(vertices.get(Integer.parseInt(parts[0]) - 1).y);
                    resultVerts.add(vertices.get(Integer.parseInt(parts[0]) - 1).z);

                    resultNorms.add(normals.get(Integer.parseInt(parts[2]) - 1).x);
                    resultNorms.add(normals.get(Integer.parseInt(parts[2]) - 1).y);
                    resultNorms.add(normals.get(Integer.parseInt(parts[2]) - 1).z);

                    resultTexCoords.add(texCoords.get(Integer.parseInt(parts[1]) - 1).x);
                    resultTexCoords.add(texCoords.get(Integer.parseInt(parts[1]) - 1).y);

                    parts = tokens[3].split("/");
                    resultVerts.add(vertices.get(Integer.parseInt(parts[0]) - 1).x);
                    resultVerts.add(vertices.get(Integer.parseInt(parts[0]) - 1).y);
                    resultVerts.add(vertices.get(Integer.parseInt(parts[0]) - 1).z);

                    resultNorms.add(normals.get(Integer.parseInt(parts[2]) - 1).x);
                    resultNorms.add(normals.get(Integer.parseInt(parts[2]) - 1).y);
                    resultNorms.add(normals.get(Integer.parseInt(parts[2]) - 1).z);

                    resultTexCoords.add(texCoords.get(Integer.parseInt(parts[1]) - 1).x);
                    resultTexCoords.add(texCoords.get(Integer.parseInt(parts[1]) - 1).y);
                }
            }

            float[] outVerts = new float[resultVerts.size()];
            for (int i = 0; i < outVerts.length; i++) {
                outVerts[i] = resultVerts.get(i);
            }
            float[] outNorms = new float[resultNorms.size()];
            for (int i = 0; i < outNorms.length; i++) {
                outNorms[i] = resultNorms.get(i);
            }
            float[] outTexCoords = new float[resultTexCoords.size()];
            for (int i = 0; i < outTexCoords.length; i++) {
                outTexCoords[i] = resultTexCoords.get(i);
            }

            return new Vertices(outVerts, outNorms, outTexCoords);

        } catch(IOException e) {
            return null;
        }
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
