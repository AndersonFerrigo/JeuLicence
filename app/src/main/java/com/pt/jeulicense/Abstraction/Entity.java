package com.pt.jeulicense.Abstraction;

public abstract class Entity {

    protected float x;
    protected float y;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public double distanceBetween(Entity e) {
        return Math.sqrt(Math.pow((double) (e.getX() - this.getX()), 2.0D) + Math.pow((double) (e.getY() - this.getY()), 2.0D));
    }
}
