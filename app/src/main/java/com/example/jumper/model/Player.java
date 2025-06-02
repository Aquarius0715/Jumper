package com.example.jumper.model;

public class Player {
    private int x = 300;
    private int y = 0;
    private final int xSize = 96;
    private final int ySize = 96;
    private int xSpeed = 3;
    private float ySpeed = 0;
    private boolean jumpFlag = false;

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

    public int getYSize() {
        return ySize;
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

    public boolean getJumpFlag() {
        return jumpFlag;
    }

    public void setJumpFlag(boolean jumpFlag) {
        this.jumpFlag = jumpFlag;
    }
}
