package io.github.mwttg.pixelatillery.example4;

import org.joml.Vector4f;

public class Particle {

    private Transform transform;
    private Vector4f color;
    private float velocity;

    public Particle(final Transform transform, final Vector4f color, final float velocity) {
        this.transform = transform;
        this.color = color;
        this.velocity = velocity;
    }

    public Transform getTransform() {
        return transform;
    }

    public void move(final float deltaTime) {
        transform.move(velocity, deltaTime);
    }

    public void setTransform(final Transform transform) {
        this.transform = transform;
    }

    public Vector4f getColor() {
        return color;
    }

    public void setColor(final Vector4f color) {
        this.color = color;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(final float velocity) {
        this.velocity = velocity;
    }
}

