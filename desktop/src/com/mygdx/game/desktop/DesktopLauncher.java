package com.mygdx.game.desktop;

import Game.Agario;
import Utils.Helper;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        setConfig();
        config.title = Helper.title;
        config.height = Helper.height;
        config.width = Helper.width;
        new LwjglApplication(new Agario(), config);
    }

    public static void setConfig() {
        Helper.title = "Agario";
        Helper.height = 800;
        Helper.width = 800;
    }
}
