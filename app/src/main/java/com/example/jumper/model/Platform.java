package com.example.jumper.model;
public class Platform extends Entity{

    private Player player;

    public Platform() {
        x = 400;
        y = 320;
        xSize = 192;
        ySize = 42;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void move() {
        if (player.getYSpeed() <= 0 && isOverlap(player)) {
            player.jump();
        }
    }
}
