package com.example.jumper.model;

public class Coin extends Entity {
    private int state = 0;
    private int countTime = 0;

    public Coin() {
        x = 150;
        y = 450;
        xSize = 75;
        ySize = 75;
    }

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
}
