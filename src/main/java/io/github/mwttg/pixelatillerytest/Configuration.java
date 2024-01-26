package io.github.mwttg.pixelatillerytest;

import io.github.mwttg.pixelartillery2d.config.OpenGlConfiguration;
import org.joml.Matrix4f;

public class Configuration {

    private Configuration() {}

    public static OpenGlConfiguration createOpenGlConfiguration() {
        return new OpenGlConfiguration(
                "Pixel ARTillery 2D Test",
                1920,
                1080,
                false,
                true,
                0.01f,
                100.0f);
    }

    public static Matrix4f createProjectionMatrix() {
        return new Matrix4f().setOrtho(0.0f, 20.0f, 0.0f, 10.0f, 0.01f, 100.0f);
    }

    public static Matrix4f createViewMatrix() {
        return new Matrix4f().setLookAt(0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    }
}
