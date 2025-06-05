package com.example.jumper.model;

import com.example.jumper.MainActivity;

public class Player extends Entity {
    private int xSpeed = 3;
    private float ySpeed = 0;
    private boolean jumpFlag = false;

    public Player(MainActivity main) {
        x = 300;
        y = 0;
        xSize = 96;
        ySize = 96;
    }

    public void move() {
        if (!jumpFlag) {
            jump();
        }
        x = x + xSpeed;
        y = (int) (y + ySpeed);

        if (x > 700) {
            x = - xSize;
        }

        if (x + xSize < 0) {
            x = 700;
        }

        if (y <= 0) {
            y = 0;
            ySpeed = 0;
            jumpFlag = false;
        }
        ySpeed = ySpeed - 0.1f;
    }

    public void jump() {
        ySpeed = 10.4f;
        jumpFlag = true;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }
}
