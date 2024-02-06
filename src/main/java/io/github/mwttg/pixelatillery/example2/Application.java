package io.github.mwttg.pixelatillery.example2;

import io.github.mwttg.pixelartillery2d.sound.Sound;
import io.github.mwttg.pixelartillery2d.sound.SoundDevice;
import org.joml.Vector3f;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        SoundDevice.initialize();
        // SoundListener exists by default, to manipulate it use the class SoundListener

        final var sound = Sound.create("./data/sound/jump.wav", true);
        var position = new Vector3f(-10.0f, 0.0f, -5.0f);

        sound.play();
        while (position.x < 10.0f) {
            position.add(0.2f, 0.0f, 0.0f);
            sound.updatePosition(position);
            Thread.sleep(50);
        }
    }
}

