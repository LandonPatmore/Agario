package Actors;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends BaseEntity {

    public Enemy(Vector2 screenPos, String name) {
        super(screenPos, 10, name);
        generateInitialMoveConstants();
    }

    private void generateInitialMoveConstants() {
        moveConstantX = MathUtils.random(-1.5f, 1.5f);
        moveConstantY = MathUtils.random(-1.5f, 1.5f);
    }


}
