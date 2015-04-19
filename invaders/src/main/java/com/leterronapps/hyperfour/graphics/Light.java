package com.leterronapps.hyperfour.graphics;

import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 03/04/15.
 */
public class Light extends SceneObject {

    /** Ambient intensity */
    public final Vector3D La;

    /** Diffuse intensity */
    public final Vector3D Ld;

    /** Specular intensity */
    public final Vector3D Ls;

    public Light(Vector3D position) {
        super(position);
        La = new Vector3D(1,1,1);
        Ld = new Vector3D(1,1,1);
        Ls = new Vector3D(1,1,1);
    }

    public Light(Vector3D position, Vector3D La, Vector3D Ld, Vector3D Ls) {
        super(position);
        this.La = La;
        this.Ld = Ld;
        this.Ls = Ls;
    }
}
