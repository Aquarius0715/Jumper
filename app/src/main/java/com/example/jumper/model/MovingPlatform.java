package com.example.jumper.model;

public class MovingPlatform extends Platform{
    private final int xSpeed;
    private boolean vector = true;

    public MovingPlatform() {
        super.setX(50);
        super.setY(700);
        xSpeed = 2;
    }
    
    public void move() {
        if (getX() >= 550 - getXSize()) {
            vector = false;
        } else if (getX() <= 50) {
            vector = true;
        }
        if (vector) {
            setX(getX() + xSpeed);
        } else {
            setX(getX() + xSpeed);
        }
    }
}
