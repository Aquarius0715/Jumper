package com.example.jumper.model;

import com.example.jumper.MainActivity;

public class Player extends Entity {
    private int xSpeed = 3;
    private float ySpeed = 0;
    private int yMax = 0;
    private boolean jumpFlag = false;
    private boolean isDead = false;
    private boolean isClear = false;
    private int point = 0;

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

        if (y > yMax) {
            yMax = y;
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

    public void highJump() {
        ySpeed = 14.0f;
        jumpFlag = true;
    }

    public void dead() {
        isDead = true;
    }

    public void clear() {
        isClear = true;
    }

    public int getPoint() {
        return point;
    }

    public void addPoint(int point) {
        this.point += point;
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

    public int getYMax() {
        return yMax;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isClear() {
        return isClear;
    }
}
