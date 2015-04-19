package com.leterronapps.hyperfour.graphics;

import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 03/04/15.
 */
public class Material {

    /** Ambient reflectivity */
    public final Vector3D Ka;

    /** Diffuse reflectivity */
    public final Vector3D Kd;

    /** Specular reflectivity */
    public final Vector3D Ks;

    /** Shininess of Material */
    public final float shininess;

    public Material() {
        Ka = new Vector3D(1,1,1);
        Kd = new Vector3D(1,1,1);
        Ks = new Vector3D(1,1,1);
        shininess = 1f;
    }

    public Material(Vector3D Ka, Vector3D Kd, Vector3D Ks, float shininess) {
        this.Ka = Ka;
        this.Kd = Kd;
        this.Ks = Ks;
        this.shininess = shininess;
    }
}
