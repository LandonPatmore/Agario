package Game;

import Screens.GameScreen;
import com.badlogic.gdx.Game;

public class Agario extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
