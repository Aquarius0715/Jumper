package com.example.jumper.model;

public class UFO {
    private int x = 0;
    private int y = 1100;
    private int xSize = 96;
    private int ySize = 66;
    private int xSpeed = 2;
    private boolean vector = true;

    public void move() {
        if (x >= 700 - xSize) {
            vector = false;
        } else if (x <= 0) {
            vector = true;
        }
        if (vector) {
            x += xSpeed;
        } else {
            x -= xSpeed;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public boolean getVector() {
        return vector;
    }
}
