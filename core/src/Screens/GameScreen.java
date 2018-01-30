package Screens;

import Actors.Consumable;
import Actors.Enemy;
import Utils.Helper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {

    // Entities
    private Array<Consumable> consumables = new Array<Consumable>();
    private Array<Enemy> enemies = new Array<Enemy>();

    // Renderer
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public GameScreen() {
        generateConsumables();
        generateEnemies();
        Helper.setDebug(true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        placeConsumables();
        placeEnemies();
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void placeEnemies() {
        for (Enemy e : enemies) {
            checks(e);
            moveFuncs(e);
            shapeRenderer.setColor(e.R, e.G, e.B, 1);
            shapeRenderer.circle(e.x, e.y, e.radius);
        }
    }

    private void checks(Enemy e) {
        checkEnemyAteConsumable(e);
        checkEnemyAteEnemy(e);
        checkEnemyGoingOffScreen(e);
    }

    private void moveFuncs(Enemy e) {
        e.move();
        e.changeDirection();
    }

    private void checkEnemyAteEnemy(Enemy e) {
        for (int i = 0; i < enemies.size; i++) {
            Enemy ee = enemies.get(i);
            if (e.overlaps(ee) && e.radius > ee.radius) {
                enemies.removeValue(ee, false);
//                Gdx.app.debug("Enemy at Enemy", e.getName() + " ate: " + ee.getName());
                e.radius += ee.radius * 0.4f;
            }
        }
    }

    private void checkEnemyAteConsumable(Enemy e) {
        for (Consumable c : consumables) {
            if (e.overlaps(c)) {
                consumables.removeValue(c, false);
//                Gdx.app.debug("Enemy at Consumable", e.getName() + " ate: " + c.getName());
                e.radius += 1;
            }
        }
    }

    private void checkEnemyGoingOffScreen(Enemy e) {
        float H = Helper.height - .01f;
        float W = Helper.width - .01f;

        if (((e.x + e.radius) > W - .01) || ((e.x - e.radius) < .01f) || ((e.y + e.radius) > H - .01) || ((e.y - e.radius) < .01f)) {
//            Gdx.app.debug("Constraints", e.getName() + " hit edge of screen.");
            e.setMoveConstantX(-e.moveConstantX);
            e.setMoveConstantY(-e.moveConstantY);
        }
    }

    private void placeConsumables() {
        for (Consumable c : consumables) {
            shapeRenderer.setColor(c.R, c.G, c.B, 1);
            shapeRenderer.circle(c.x, c.y, c.radius);
        }
    }

    private void generateConsumables() {
        for (int i = 0; i < 300; i++) {
            consumables.add(new Consumable(new Vector2(randX(), randY()), String.valueOf(i)));
        }
    }

    private void generateEnemies() {
        for (int i = 0; i < 10; i++) {
            enemies.add(new Enemy(new Vector2(randX(), randY()), String.valueOf(i)));
        }
    }

    private float randX() {
        return MathUtils.random(0, Helper.width);
    }

    private float randY() {
        return MathUtils.random(0, Helper.height);
    }
}
