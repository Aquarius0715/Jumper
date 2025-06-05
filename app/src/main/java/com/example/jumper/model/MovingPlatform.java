package com.example.jumper.model;

public class MovingPlatform extends Platform {
    private final int xSpeed;
    private boolean vector = true;
    private Player player;

    public MovingPlatform() {
        x = 50;
        y = 700;
        xSpeed = 2;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void move() {
        if (getX() >= 550 - getXSize()) {
            vector = false;
        } else if (getX() <= 50) {
            vector = true;
        }
        if (vector) {
            setX(getX() + xSpeed);
        } else {
            setX(getX() - xSpeed);
        }

        if (player.getYSpeed() <= 0 && isOverlap(player)) {
            player.jump();
        }
    }
}
