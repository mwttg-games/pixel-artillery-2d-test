package io.github.mwttg.pixelatillerytest;

import io.github.mwttg.pixelartillery2d.mainloop.AbstractMainLoop;
import io.github.mwttg.pixelartillery2d.sprites.*;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL41;

import java.util.List;

public class MainLoop extends AbstractMainLoop {

    private final int defaultShaderProgramId = ShaderProgram.createDefaultShader();
    private final Uniform defaultUniform = Uniform.create(defaultShaderProgramId);

    private final int customShaderProgramId = ShaderProgram.createFrom("./data/shaders/vertex.glsl", "./data/shaders/fragment.glsl");
    private final Uniform customUniform = Uniform.create(customShaderProgramId);

    private final Matrix4f view = Configuration.createViewMatrix();
    private final Matrix4f projection = Configuration.createProjectionMatrix();

    private final Sprite level = StaticSprite.create(20.0f, 10.f, "./data/sprites/level.png");
    private final Matrix4f levelModel = new Matrix4f().translate(0.0f, 0.0f, -1.0f);

    private final Sprite led1 = AnimatedSprite.create(10.0f, 1.0f, "./data/sprites/leds.png", List.of(800, 150, 150, 150, 150, 150, 350, 350, 350, 550, 550));
    private final Sprite led2 = AnimatedSprite.create(10.0f, 1.0f, "./data/sprites/leds.png", List.of(800, 150, 150, 150, 150, 150, 350, 350, 350, 550, 550), AnimationType.ONCE);
    private final Sprite led3 = AnimatedSprite.create(10.0f, 1.0f, "./data/sprites/leds.png", List.of(800, 150, 150, 150, 150, 150, 350, 350, 350, 550, 550), AnimationType.ALTERNATING);
    private final Matrix4f ledModel1 = new Matrix4f().translate(3.0f, 5.0f, -0.5f);
    private final Matrix4f ledModel2 = new Matrix4f().translate(3.0f, 7.0f, -0.5f);
    private final Matrix4f ledModel3 = new Matrix4f().translate(3.0f, 9.0f, -0.5f);

    @Override
    protected void initialize() {
        level.flipHorizontal();
        led2.flipHorizontal();
    }

    @Override
    protected void execute() {
        GL41.glUseProgram(customShaderProgramId);
        level.draw(customUniform, levelModel, view, projection);

        GL41.glUseProgram(defaultShaderProgramId);
        led1.draw(defaultUniform, ledModel1, view, projection);
        led2.draw(defaultUniform, ledModel2, view, projection);
        led3.draw(defaultUniform, ledModel3, view, projection);
    }
}
