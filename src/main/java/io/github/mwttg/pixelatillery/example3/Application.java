package io.github.mwttg.pixelatillery.example3;

/**
 * Example for using the library 'Pixel ARTillery 2D', for rendering an instanced static sprite.
 * (Draw 1500 balls on a background - one OpenGL draw call for the balls one OpenGL draw call for the background)
 */
public class Application {

    public static void main(String[] args) {
        final var demo = new Demo();
        demo.start();
    }
}
