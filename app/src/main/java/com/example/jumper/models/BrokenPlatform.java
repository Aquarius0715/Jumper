package com.example.jumper.models;

public class BrokenPlatform extends Platform{
    private int timeCount;
    private int state;
    private Player player;
    private boolean isJumped = false;

    public BrokenPlatform(int initY) {
        super(initY);
        //x = 50;
        //y = 1000;
        timeCount = 0;
        state = 0;
    }

    public void move() {
        if (isJumped) {
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

        if (player.getYSpeed() <= 0 && isOverlap(player) && state < 4) {
            player.jump();
            isJumped = true;
            onOverlapListener.overlap();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getState() {
        return state;
    }
}
