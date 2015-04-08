package com.leterronapps.finalyearproject.game3d;

import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.graphics.HFShader;
import com.leterronapps.hyperfour.util.Vector3D;

import java.util.Vector;

/**
 * Created by williamlea on 08/04/15.
 */
public class InvaderRow extends SceneObject {

    private Vector<Invader> row;

    private float rowMovement;

    public InvaderRow(HFScene scene, Vector3D position) {
        super(scene, position);
        row = new Vector<>();
        rowMovement = -3f;
        int rowWidth = 25;
        int offset = 0;
        for(int i = 0; i < 5; i++) {
            Invader invader = new Invader(scene, new Vector3D((position.x - rowWidth / 2) + offset,position.y,position.z));
            invader.setMovement(rowMovement);
            invader.setType(Invader.InvaderType.TYPE_ONE);
            row.add(invader);
            offset += 5;
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        position.add(rowMovement * deltaTime,0,0);
        for(Invader invader : row) {
            invader.update(deltaTime);
        }

        if(position.x < GameController.getInstance().MIN_X) {
            setRowMovement(3f);
            advanceRow();
        } else if(position.x > GameController.getInstance().MAX_X) {
            setRowMovement(-3f);
            advanceRow();
        }
    }

    @Override
    public void render(HFShader shader) {
        super.render(shader);
        for(Invader invader : row) {
            invader.render(shader);
        }
    }

    private void setRowMovement(float rowMovement) {
        this.rowMovement = rowMovement;
        for(Invader invader : row) {
            invader.setMovement(rowMovement);
        }
    }

    private void advanceRow() {
        for(Invader invader : row) {
            invader.position.add(0,0,3);
        }
    }

}
