package Actors;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class BaseEntity extends Actor {

    private Vector2 screenPos;
    float moveConstantX;
    float moveConstantY;

    // Random color for entity
    final float R = MathUtils.random();
    final float G = MathUtils.random();
    final float B = MathUtils.random();

    // Pixmap and Texture to generate Entity
    private Pixmap pixmap;
    private Texture texture;

    BaseEntity(Vector2 screenPos, int size) {
        this.screenPos = screenPos;
        moveConstantX = 0;
        moveConstantY = 0;
        setMoveConstants();
        generatePixmap(size);
    }

    private void generatePixmap(int size) {
        pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);
        pixmap.setColor(R, G, B, 1);
        pixmap.fillCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, size / 2 - 1);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    private void setMoveConstants() {
        if (this instanceof Consumable) return;
        moveConstantX = MathUtils.random(-2.5f, 2.5f);
        moveConstantY = MathUtils.random(-2.5f, 2.5f);
    }

    private void changeDirection() {
        float probability = MathUtils.random();

        if (probability > 0.99f) {
            setMoveConstants();
        }
    }

    public void setScreenPos(float x, float y) {
        screenPos.x = x;
        screenPos.y = y;
    }

    public Vector2 getScreenPos() {
        return screenPos;
    }

    public void move() {
        if (this instanceof Consumable) return;
        screenPos.x += moveConstantX;
        screenPos.y += moveConstantY;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, screenPos.x, screenPos.y);
        move();
        changeDirection();
    }
}
