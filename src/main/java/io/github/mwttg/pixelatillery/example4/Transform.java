package io.github.mwttg.pixelatillery.example4;

import org.joml.Matrix4f;

public class Transform {

    private float x;
    private float y;
    private float scaleFactor;

    public Transform(final float x, final float y, final float scaleFactor) {
        this.x = x;
        this.y = y;
        this.scaleFactor = scaleFactor;
    }

    public float getY() {
        return y;
    }

    public Matrix4f getTransform(final float z) {
        return new Matrix4f().translate(x, y, z).scale(scaleFactor, scaleFactor, 1.0f);
    }

    public void move(final float velocity, final float deltaTime) {
        final float deltaY = velocity * deltaTime;
        y = y - deltaY;
    }
}
