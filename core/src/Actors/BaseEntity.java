package Actors;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class BaseEntity extends Circle {

    // Move constants for the circle that can be dynamically changed
    public float moveConstantX;
    public float moveConstantY;

    // Information about entitiy
    private final String name;

    // Random color for entity
    public final float R = MathUtils.random();
    public final float G = MathUtils.random();
    public final float B = MathUtils.random();

    BaseEntity(Vector2 position, float radius, String name) {
        super(position, radius);
        this.name = name;
        setMoveConstants();
    }

    public String getName() {
        return name;
    }

    private void setMoveConstants() {
        if (this instanceof Consumable) return;
        moveConstantX = MathUtils.random(-1.5f, 1.5f);
        moveConstantY = MathUtils.random(-1.5f, 1.5f);
    }

    public void setMoveConstantX(float moveConstantX) {
        this.moveConstantX = moveConstantX;
    }

    public void setMoveConstantY(float moveConstantY) {
        this.moveConstantY = moveConstantY;
    }

    public void changeDirection() {
        float probability = MathUtils.random();
        float innerProbability = MathUtils.random();

        if (probability > 0.99f) {
            if (innerProbability > 0.99f) {
                setMoveConstantX(MathUtils.random(-10.0f, 10.0f));
                setMoveConstantY(MathUtils.random(-10.0f, 10.0f));
            } else {
                setMoveConstants();
            }
        }
    }

    public void move() {
        if (this instanceof Consumable) return;
        x += moveConstantX;
        y += moveConstantY;
    }
}
