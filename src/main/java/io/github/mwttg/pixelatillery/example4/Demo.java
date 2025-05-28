package io.github.mwttg.pixelatillery.example4;

import io.github.mwttg.pixelartillery2d.graphic.InstancedParticleUniform;
import io.github.mwttg.pixelartillery2d.graphic.InstancedParticles;
import io.github.mwttg.pixelartillery2d.graphic.ShaderProgram;
import io.github.mwttg.pixelartillery2d.graphic.SimpleGraphicApplication;
import io.github.mwttg.pixelartillery2d.timer.Timer;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL41;

import java.util.List;

public class Demo extends SimpleGraphicApplication {

    // used resolution 1920x1080 scaled to 640x360 using a grid of 10x10 pixels
    // one Tile = 10x10 --> Screen 64x36 Tiles
    public static final float RIGHT = 640.0f / 10; // 64
    public static final float TOP = 360.0f / 10; // 36

    private final Matrix4f view;
    private final Matrix4f projection;

    private final int particleShaderProgramId;
    private final InstancedParticleUniform particleUniform;
    private final InstancedParticles instancedParticles;

    private final Timer timer;

    private ParticleSystem particleSystem;

    protected Demo() {
        super("Pixel ARTillery 2D Test", 1920, 1080);
        this.view = new Matrix4f().setLookAt(0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        this.projection = new Matrix4f().setOrtho(0.0f, RIGHT, 0.0f, TOP, 0.0f, 10.0f);

        this.particleShaderProgramId = ShaderProgram.createDefaultParticleShader();
        this.particleUniform = InstancedParticleUniform.create(particleShaderProgramId);
        // this.instancedParticles = InstancedParticles.create(0.1f, 0.1f);
        this.instancedParticles = InstancedParticles.create(0.5f, 0.5f);

        this.timer = Timer.initialize();
    }

    @Override
    protected void initialize() {
        GL41.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        particleSystem = new ParticleSystem();
    }

    @Override
    protected void gameLoop() {
        final float deltaTime = timer.getDeltaTime();
        particleSystem.update(deltaTime);

        GL41.glUseProgram(particleShaderProgramId);
        final Pair<List<Vector4f>, List<Matrix4f>> colorsAndTransforms = particleSystem.getColorsAndTransforms();
        instancedParticles.draw(particleUniform, colorsAndTransforms.left(), colorsAndTransforms.right(), view, projection);
    }
}
