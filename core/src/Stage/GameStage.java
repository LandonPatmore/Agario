package Stage;

import Actors.Consumable;
import Actors.Enemy;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage {

    public GameStage(Viewport viewport) {
        super(viewport);
        generateConsumables();
        generateEnemies();
    }

    private void generateConsumables() {
        for (int i = 0; i < 100; i++) {
            addActor(new Consumable(new Vector2(randX(), randY())));
        }
    }

    private void generateEnemies() {
        for (int i = 0; i < 10; i++) {
            Enemy e = new Enemy(new Vector2(randX(), randY()));
            addActor(e);
        }
    }

    private float randX() {
        return MathUtils.random(0, getWidth());
    }

    private float randY() {
        return MathUtils.random(0, getHeight());
    }
}
