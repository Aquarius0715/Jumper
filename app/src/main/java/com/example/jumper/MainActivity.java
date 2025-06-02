package com.example.jumper;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.jumper.helpers.BaseActivity;
import com.example.jumper.model.BrokenPlatform;
import com.example.jumper.model.Castle;
import com.example.jumper.model.Coin;
import com.example.jumper.model.MovingPlatform;
import com.example.jumper.model.Platform;
import com.example.jumper.model.Player;
import com.example.jumper.model.UFO;

public class MainActivity extends BaseActivity {

    //-----------
    // Images
    //-----------
    private Bitmap playerRightImage;
    private Bitmap playerLeftImage;
    private Bitmap platformImage;
    private Bitmap platform1Image;
    private Bitmap platform2Image;
    private Bitmap platform3Image;
    private Bitmap coin1Image;
    private Bitmap coin2Image;
    private Bitmap coin3Image;
    private Bitmap castleImage;
    private Bitmap ufoRightImage;
    private Bitmap ufoLeftImage;

    //-----------
    // Views
    //-----------
    private ConstraintLayout constraintLayout;
    private ImageView playerImageView;
    private ImageView platformImageView;
    private ImageView brokenPlatformImageView;
    private ImageView movingPlatformImageView;
    private ImageView coinImageView;
    private ImageView castleImageView;
    private ImageView ufoImageView;

    //-----------
    // Models
    //-----------
    private Player player;
    private Platform platform;
    private MovingPlatform movingPlatform;
    private BrokenPlatform brokenPlatform;
    private Coin coin;
    private Castle castle;
    private UFO ufo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        gravityEnabled = true;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        //-----------
        // Load View
        //-----------
        constraintLayout = findViewById(R.id.main);

        //-----------
        // Load Images
        //-----------

        playerRightImage = loadImage(R.drawable.player_right);
        playerLeftImage = loadImage(R.drawable.player_left);
        platformImage = loadImage(R.drawable.platform);
        platform1Image = loadImage(R.drawable.platform1);
        platform2Image = loadImage(R.drawable.platform2);
        platform3Image = loadImage(R.drawable.platform3);
        coin1Image = loadImage(R.drawable.coin1);
        coin2Image = loadImage(R.drawable.coin2);
        coin3Image = loadImage(R.drawable.coin3);
        castleImage = loadImage(R.drawable.castle);
        ufoRightImage = loadImage(R.drawable.ufo_right);
        ufoLeftImage = loadImage(R.drawable.ufo_left);



        //-----------
        // Image Views
        //-----------
        playerImageView = new ImageView(this);
        platformImageView = new ImageView(this);
        movingPlatformImageView = new ImageView(this);
        brokenPlatformImageView = new ImageView(this);
        coinImageView = new ImageView(this);
        castleImageView = new ImageView(this);
        ufoImageView = new ImageView(this);

        constraintLayout.addView(playerImageView);
        constraintLayout.addView(platformImageView);
        constraintLayout.addView(movingPlatformImageView);
        constraintLayout.addView(brokenPlatformImageView);
        constraintLayout.addView(coinImageView);
        constraintLayout.addView(castleImageView);
        constraintLayout.addView(ufoImageView);

        //-----------
        // Create Player Instance
        //-----------
        player = new Player();
        platform = new Platform();
        movingPlatform = new MovingPlatform();
        brokenPlatform = new BrokenPlatform();
        coin = new Coin();
        castle = new Castle();
        ufo = new UFO();

    }

    @Override
    public void update() {

        //-----------
        // モデルへの更新
        //-----------
        int accelX = (int) accelerationController.x;
        player.setXSpeed(accelX * -1);

        //-----------
        // モデルの更新
        //-----------
        player.move();
        movingPlatform.move();
        ufo.move();
        brokenPlatform.move();
        coin.move();

        //-----------
        //　モデルの表示
        //-----------
        Bitmap playerImage;
        if (player.getXSpeed() >= 0) {
            playerImage = playerRightImage;
        } else {
            playerImage = playerLeftImage;
        }
        drawImage(
                player.getX(),
                player.getY(),
                player.getXSize(),
                player.getYSize(),
                playerImage,
                playerImageView
        );

        drawImage(
                platform.getX(),
                platform.getY(),
                platform.getXSize(),
                platform.getYSize(),
                platformImage,
                platformImageView
        );

        drawImage(
                movingPlatform.getX(),
                movingPlatform.getY(),
                movingPlatform.getXSize(),
                movingPlatform.getYSize(),
                platformImage,
                movingPlatformImageView
        );

        Bitmap coinImage;
        switch (coin.getState()) {
            case 0:
                coinImage = coin1Image;
                break;
            case 1:
                coinImage = coin2Image;
                break;
            case 2:
                coinImage = coin3Image;
                break;
            default:
                coinImage = coin1Image;

        }
        drawImage(
                coin.getX(),
                coin.getY(),
                coin.getxSize(),
                coin.getySize(),
                coinImage,
                coinImageView
        );

        drawImage(
                castle.getX(),
                castle.getY(),
                castle.getxSize(),
                castle.getySize(),
                castleImage,
                castleImageView
        );

        Bitmap ufoImage;
        if (ufo.getVector()) {
            ufoImage = ufoRightImage;
        } else {
            ufoImage = ufoLeftImage;
        }
        drawImage(
                ufo.getX(),
                ufo.getY(),
                ufo.getxSize(),
                ufo.getySize(),
                ufoImage,
                ufoImageView
        );

        Bitmap brokenPlatformImage;
        switch (brokenPlatform.getState()) {
            case 0:
                brokenPlatformImage = platformImage;
                break;
            case 1:
                brokenPlatformImage = platform1Image;
                break;
            case 2:
                brokenPlatformImage = platform2Image;
                break;
            case 3:
                brokenPlatformImage = platform3Image;
                break;
            case 4:
                brokenPlatformImageView.setVisibility(ImageView.GONE);
            default:
                brokenPlatformImage = platformImage;
        }
        drawImage(
                brokenPlatform.getX(),
                brokenPlatform.getY(),
                brokenPlatform.getXSize(),
                brokenPlatform.getYSize(),
                brokenPlatformImage,
                brokenPlatformImageView
        );


    }

}