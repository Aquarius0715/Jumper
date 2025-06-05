package com.example.jumper.model;

public class Entity {
    protected int x;
    protected int y;
    protected int xSize;
    protected int ySize;

    public boolean isOverlapX(Entity entity) {
        if (getRight() < entity.getLeft()) {
            return false;
        } else return entity.getRight() >= getLeft();
    }

    public boolean isOverlapY(Entity entity) {
        if (getTop() < entity.getBottom()) {
            return false;
        }
        return entity.getTop() >= getBottom();
    }

    public boolean isOverlap(Entity entity) {
        return isOverlapX(entity) && isOverlapY(entity);
    }

    public int getRight() {
        return x + xSize - 1;
    }

    public int getLeft() {
        return x;
    }

    public int getTop() {
        return y + ySize - 1;
    }

    public int getBottom() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXSize() {
        return xSize;
    }

    public void setXSize(int xSize) {
        this.xSize = xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public void setYSize(int ySize) {
        this.ySize = ySize;
    }
}
