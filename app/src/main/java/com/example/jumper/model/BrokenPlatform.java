package com.example.jumper.model;

public class BrokenPlatform extends Platform{
    private int timeCount;

    private int state;

    public BrokenPlatform() {
        super.setX(50);
        super.setY(1000);
        timeCount = 0;
        state = 0;
    }

    public void move() {
        timeCount += 1;
        if (timeCount >= 0 && timeCount < 100) {
            state = 0;
        } else if (timeCount >= 100 && timeCount < 200) {
            state = 1;
        } else if (timeCount >= 200 && timeCount < 300) {
            state = 2;
        } else if (timeCount >= 300 && timeCount < 400) {
            state = 3;
        } else {
            state = 4;
        }
    }

    public int getState() {
        return state;
    }
}
