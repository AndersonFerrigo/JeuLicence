package com.pt.jeulicense.Entities;

import com.pt.jeulicense.Abstraction.Entity;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Ball extends Entity {
    public static float RADIUS = 30.0F;
    private Vector2D velocity;

    public Ball(float x, float y) {
        super(x, y);
        this.velocity = Vector2D.ZERO;
    }

    public void accelerate(Vector2D v) {
        this.velocity = this.velocity.add(v);
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void move() {
        this.x = (float) ((double) this.x + this.velocity.getX());
        this.y = (float) ((double) this.y + this.velocity.getY());
    }

    public void setX(int x) {
        this.x = (float) x;
    }

    public void setY(int y) {
        this.y = (float) y;
    }
}
