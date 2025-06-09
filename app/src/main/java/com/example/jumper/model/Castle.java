package com.example.jumper.model;

public class Castle extends Entity {
    private Player player;

    public Castle() {
        x = 500;
        //y = 1200;
        y = 6500;
        xSize = 192;
        ySize = 192;
    }

    public void move() {
        if (isOverlap(player)) {
            player.clear();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
