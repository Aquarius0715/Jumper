package com.example.jumper.models;
public class Platform extends Entity{

    private Player player;

    public Platform(int initY) {
        x = (int) (Math.random() * 500);
        y = initY + (int) (Math.random() * 100 - 50);
        xSize = 192;
        ySize = 42;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void move() {
        if (player.getYSpeed() <= 0 && isOverlap(player)) {
            player.jump();
            onOverlapListener.overlap();
        }
    }
}
