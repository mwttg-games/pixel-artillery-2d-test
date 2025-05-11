package io.github.mwttg.pixelatillery.example3;

import io.github.mwttg.pixelartillery2d.graphic.*;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL41;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo extends SimpleGraphicApplication {

    // used resolution 1920x1080 scaled to 640x360 using a grid of 10x10 pixels
    // one Tile = 10x10 --> Screen 64x36 Tiles
    private static final float RIGHT = 640.0f / 10; // 64
    private static final float TOP = 360.0f / 10; // 36

    private static final Random RANDOM = new Random();

    private final Matrix4f view;
    private final Matrix4f projection;

    private final int instancedShaderProgramId;
    private final InstancedUniform instancedUniform;
    private final InstancedStaticSprite instancedSprite;
    private List<Matrix4f> modelMatrices;

    private final int shaderProgramId;
    private final Uniform uniform;
    private final Sprite background;
    private final Matrix4f backgroundModel;

    protected Demo() {
        super("Pixel ARTillery 2D Test", 1920, 1080);
        this.view = new Matrix4f().setLookAt(0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        this.projection = new Matrix4f().setOrtho(0.0f, RIGHT, 0.0f, TOP, 0.0f, 10.0f);

        this.instancedShaderProgramId = ShaderProgram.createDefaultInstancedShader();
        this.instancedUniform = InstancedUniform.create(instancedShaderProgramId);
        this.instancedSprite = InstancedStaticSprite.create(1.0f, 1.0f, "./data/sprites/ball.png");

        this.shaderProgramId = ShaderProgram.createDefaultShader();
        this.uniform = Uniform.create(shaderProgramId);
        this.background = StaticSprite.create(64.0f, 36.f, "./data/sprites/level.png");
        this.backgroundModel = new Matrix4f().translate(0.0f, 0.0f, 0.5f);
    }

    @Override
    protected void initialize() {
        modelMatrices = createModelMatrices();
    }

    @Override
    protected void gameLoop() {
        GL41.glUseProgram(shaderProgramId);
        background.draw(uniform, backgroundModel, view, projection);

        GL41.glUseProgram(instancedShaderProgramId);
        instancedSprite.draw(instancedUniform, modelMatrices, view, projection);
    }

    private List<Matrix4f> createModelMatrices() {
        List<Matrix4f> result = new ArrayList<>();
        for (int index = 0; index < 1500; index++) {
            final float x = RANDOM.nextFloat() * (63.0f);
            final float y = RANDOM.nextFloat() * (35.0f);
            final Matrix4f model = new Matrix4f().translate(x, y, 1.0f);
            result.add(model);
        }
        return result;
    }
}
