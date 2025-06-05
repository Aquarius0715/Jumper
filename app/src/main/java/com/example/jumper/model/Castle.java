package com.example.jumper.model;

public class Castle extends Entity {
    private Player player;

    public Castle() {
        x = 500;
        y = 1200;
        xSize = 192;
        ySize = 192;
    }

    public void move() {
        if (player.getYSpeed() <= 0 && isOverlap(player)) {
            player.clear();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
