package io.github.mwttg.pixelatillerytest;

import io.github.mwttg.pixelartillery2d.cleanup.CleanUp;
import io.github.mwttg.pixelartillery2d.window.GameWindow;

public class Application {

    public static void main(String[] args) {
        final var config = Configuration.createOpenGlConfiguration();
        final var windowId = GameWindow.create(config);
        final var gameLoop = new MainLoop();
        gameLoop.loop(windowId);
        CleanUp.purge();
    }
}
