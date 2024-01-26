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

    private final Sprite battery = AnimatedSprite.create(1.0f, 2.0f, "./data/sprites/battery.png", List.of(150, 150, 150, 150, 150, 150, 150, 150, 150), AnimationType.ALTERNATING);
    private final Matrix4f batteryModel = new Matrix4f().translate(1.0f, 2.0f, -0.5f);

    @Override
    protected void execute() {

        GL41.glUseProgram(customShaderProgramId);
        level.draw(customUniform, levelModel, view, projection);
        battery.draw(defaultUniform, batteryModel, view, projection);

    }
}
