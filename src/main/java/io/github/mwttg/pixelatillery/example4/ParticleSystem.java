package io.github.mwttg.pixelatillery.example4;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleSystem {

    private static final int EMIT_MAX = 2;
    private static final float Z_LAYER = 0.5f;
    private static final float MIN_X = 2.0f;
    private static final float MAX_X = 61.0f;
    private static final float MIN_Y = -1.0f;
    private static final float MAX_Y = 36.0f;

    private static final int MAX_PARTICLES = 200;

    private List<Particle> particles;

    ParticleSystem() {
        this.particles = new ArrayList<>();
    }

    public Pair<List<Vector4f>, List<Matrix4f>> getColorsAndTransforms() {
        List<Vector4f> colors = new ArrayList<>();
        List<Matrix4f> transforms = new ArrayList<>();
        for (final Particle particle : particles) {
            colors.add(particle.getColor());
            transforms.add(particle.getTransform().getTransform(particle.getColor().x()));
        }

        return new Pair<>(colors, transforms);
    }

    public void update(final float deltaTime) {
        if (particles.size() < MAX_PARTICLES) {
            emitParticles();
        }
        move(deltaTime);
    }

    private void move(final float deltaTime) {
        for (final Particle particle : particles) {
            if (particle.getTransform().getY() < MIN_Y) {

                final Particle resettedParticle = createRandomParticle();
                particle.setTransform(resettedParticle.getTransform());
                particle.setColor(resettedParticle.getColor());
                particle.setVelocity(resettedParticle.getVelocity());
                continue;
            }

            particle.move(deltaTime);
        }
    }

    private void emitParticles() {
        final Random random = new Random();
        final int size = random.nextInt(EMIT_MAX);
        for (int index = 0; index < size; index++) {
            final Particle particle = createRandomParticle();
            particles.add(particle);
        }
    }

    private Particle createRandomParticle() {
        final Random random = new Random();

        final float x = random.nextFloat(MAX_X - MIN_X) + MIN_X;
        final float y = MAX_Y + 1.0f;

        final float scaleFactor = random.nextFloat() * (4.0f - 0.2f) + 0.2f;
        final float greyScale = random.nextFloat(0.8f);
        final Vector4f color = new Vector4f(greyScale, greyScale, greyScale, 1.0f);
        final Transform transform = new Transform(x, y, scaleFactor);
        final float velocity = (greyScale * 10.0f) + 10.0f;

        return new Particle(transform, color, velocity);
    }
}
