package com.example.jumper.model;

public class UFO extends Entity {
    private final int xSpeed = 2;
    private boolean vector = true;

    public UFO() {
        x = 0;
        y = 1100;
        xSize = 96;
        ySize = 66;
    }

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

    public boolean getVector() {
        return vector;
    }
}
