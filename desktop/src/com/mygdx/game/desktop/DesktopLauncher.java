package com.mygdx.game.desktop;

import Game.Agario;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        setConfig(config);
        new LwjglApplication(new Agario(), config);
    }

    public static void setConfig(LwjglApplicationConfiguration config) {
        config.title = "Agario";
        config.height = 800;
        config.width = 800;
    }
}
