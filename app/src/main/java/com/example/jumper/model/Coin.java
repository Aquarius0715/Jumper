package com.example.jumper.model;

public class Coin {
    private int x = 150;
    private int y = 450;
    private int xSize = 75;
    private int ySize = 75;
    private int state = 0;
    private int countTime = 0;

    public void move() {
        countTime += 1;
        if (countTime >= 0 && countTime < 10) {
            state = 0;
        } else if (countTime >= 10 && countTime < 20) {
            state = 1;
        } else if (countTime >= 20 && countTime < 30) {
            state = 2;
            countTime = 0;
        }
    }

    public int getState() {
        return state;
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
}
