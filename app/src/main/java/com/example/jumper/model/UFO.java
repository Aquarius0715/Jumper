package com.example.jumper.model;

public class UFO extends Entity {
    private final int xSpeed = 2;
    private boolean vector = true;
    private Player player;

    public UFO(int initY) {
        x = (int) (Math.random() * 600);
        y = initY + (int) (Math.random() * 400 - 200);
        xSize = 96;
        ySize = 66;
    }

    public void move() {
        if (x >= 700 - xSize) {
            vector = false;
        } else if (x <= 0) {
            vector = true;
        }
        if (vector) {
            x += xSpeed;
        } else {
            x -= xSpeed;
        }

        if (isOverlap(player)) {
            player.dead();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean getVector() {
        return vector;
    }
}
