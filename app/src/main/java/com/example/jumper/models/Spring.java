package com.example.jumper.models;

public class Spring extends Entity {
    private Player player;

    public Spring(int initY) {
        x = (int) (Math.random() * 600);
        y = initY + (int) (Math.random() * 400 - 200);
        xSize = 70;
        ySize = 120;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void move() {
        if (player.getYSpeed() <= 0 && isOverlap(player)) {
            player.highJump();
            onOverlapListener.overlap();
        }
    }
}
