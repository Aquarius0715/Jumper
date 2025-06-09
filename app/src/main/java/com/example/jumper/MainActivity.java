package com.example.jumper;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.jumper.helpers.BaseActivity;
import com.example.jumper.model.BrokenPlatform;
import com.example.jumper.model.Castle;
import com.example.jumper.model.Coin;
import com.example.jumper.model.Entity;
import com.example.jumper.model.MovingPlatform;
import com.example.jumper.model.Platform;
import com.example.jumper.model.Player;
import com.example.jumper.model.Spring;
import com.example.jumper.model.UFO;

import java.util.LinkedList;

public class MainActivity extends BaseActivity {

    private final int PLATFORM_COUNTS = 5;
    private final int MOVING_PLATFORM_COUNTS = 5;
    private final int BROKEN_PLATFORM_COUNTS = 5;
    private final int UFO_COUNTS = 6;
    private final int COIN_COUNTS = 7;
    private final int SPRING_COUNTS = 3;

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
    private Bitmap springImage;

    //-----------
    // Views
    //-----------
    private ConstraintLayout constraintLayout;
    private ImageView playerImageView;
    private ImageView castleImageView;
    private LinkedList<ImageView> coinImageViews;
    private LinkedList<ImageView> ufoImageViews;
    private LinkedList<ImageView> platformImageViews;
    private LinkedList<ImageView> movingPlatformImageViews;
    private LinkedList<ImageView> brokenPlatformImageViews;
    private LinkedList<ImageView> springImageViews;

    private TextView gameEndTextView;
    private TextView gameClearTextView;
    private TextView pointTextView;

    //-----------
    // Models
    //-----------
    private Player player;
    private Castle castle;
    private LinkedList<UFO> ufos;
    private LinkedList<Coin> coins;
    private LinkedList<Platform> platforms;
    private LinkedList<MovingPlatform> movingPlatforms;
    private LinkedList<BrokenPlatform> brokenPlatforms;
    private LinkedList<Spring> springs;


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
        springImage = loadImage(R.drawable.spring);



        //-----------
        // Image Views
        //-----------
        playerImageView = new ImageView(this);
        castleImageView = new ImageView(this);
        coinImageViews = new LinkedList<>();
        ufoImageViews = new LinkedList<>();
        platformImageViews = new LinkedList<>();
        movingPlatformImageViews = new LinkedList<>();
        brokenPlatformImageViews = new LinkedList<>();
        springImageViews = new LinkedList<>();

        for (int i = 0; i < COIN_COUNTS; i++) {
            ImageView coinImageView = new ImageView(this);
            constraintLayout.addView(coinImageView);
            coinImageViews.add(coinImageView);
        }
        for (int i = 0; i < UFO_COUNTS; i++) {
            ImageView ufoImageView = new ImageView(this);
            constraintLayout.addView(ufoImageView);
            ufoImageViews.add(ufoImageView);
        }
        for (int i = 0; i < PLATFORM_COUNTS; i++) {
            ImageView platformImageView = new ImageView(this);
            constraintLayout.addView(platformImageView);
            platformImageViews.add(platformImageView);
        }
        for (int i = 0; i < MOVING_PLATFORM_COUNTS; i++) {
            ImageView movingPlatformImageView = new ImageView(this);
            constraintLayout.addView(movingPlatformImageView);
            movingPlatformImageViews.add(movingPlatformImageView);
        }
        for (int i = 0; i < BROKEN_PLATFORM_COUNTS; i++) {
            ImageView brokenPlatformImageView = new ImageView(this);
            constraintLayout.addView(brokenPlatformImageView);
            brokenPlatformImageViews.add(brokenPlatformImageView);
        }
        for (int i = 0; i < SPRING_COUNTS; i++) {
            ImageView springImageView = new ImageView(this);
            constraintLayout.addView(springImageView);
            springImageViews.add(springImageView);
        }

        gameEndTextView = new TextView(this);
        gameClearTextView = new TextView(this);
        pointTextView = new TextView(this);

        constraintLayout.addView(playerImageView);
        constraintLayout.addView(castleImageView);

        constraintLayout.addView(gameEndTextView);
        constraintLayout.addView(gameClearTextView);
        constraintLayout.addView(pointTextView);

        gameEndTextView.setVisibility(TextView.GONE);
        gameClearTextView.setVisibility(TextView.GONE);


        //-----------
        // Create Player Instance
        //-----------
        player = new Player(this);
        castle = new Castle();
        coins = new LinkedList<>();
        ufos = new LinkedList<>();
        platforms = new LinkedList<>();
        movingPlatforms = new LinkedList<>();
        brokenPlatforms = new LinkedList<>();
        springs = new LinkedList<>();

        for (int i = 0; i < COIN_COUNTS; i++) {
            Coin coin = new Coin(450 + i * 800);
            coins.add(coin);
        }
        for (int i = 0; i < UFO_COUNTS; i++) {
            UFO ufo = new UFO(1100 + i * 1100);
            ufos.add(ufo);
        }
        for (int i = 0; i < PLATFORM_COUNTS; i++) {
            Platform platform = new Platform(400 + i * 1200);
            platforms.add(platform);
        }
        for (int i = 0; i < MOVING_PLATFORM_COUNTS; i++) {
            MovingPlatform movingPlatform = new MovingPlatform(800 + i * 1200);
            movingPlatforms.add(movingPlatform);
        }
        for (int i = 0; i < BROKEN_PLATFORM_COUNTS; i++) {
            BrokenPlatform brokenPlatform = new BrokenPlatform(1200 + i * 1200);
            brokenPlatforms.add(brokenPlatform);
        }
        for (int i = 0; i < SPRING_COUNTS; i++) {
            Spring spring = new Spring(1800 + i * 1800);
            springs.add(spring);
        }

        castle.setPlayer(player);
        for (Coin coin : coins) {
            coin.setPlayer(player);
        }
        for (UFO ufo : ufos) {
            ufo.setPlayer(player);
        }
        for (Platform platform : platforms) {
            platform.setPlayer(player);
        }
        for (MovingPlatform movingPlatform : movingPlatforms) {
            movingPlatform.setPlayer(player);
        }
        for (BrokenPlatform brokenPlatform : brokenPlatforms) {
            brokenPlatform.setPlayer(player);
        }
        for (Spring spring : springs) {
            spring.setPlayer(player);
        }
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
        castle.move();


        for (Coin coin : coins) {
            coin.move();
        }
        for (UFO ufo : ufos) {
            ufo.move();
        }
        for (Platform platform : platforms) {
            platform.move();
        }
        for (MovingPlatform movingPlatform : movingPlatforms) {
            movingPlatform.move();
        }
        for (BrokenPlatform brokenPlatform : brokenPlatforms) {
            brokenPlatform.move();
        }
        for (Spring spring : springs) {
            spring.move();
        }

        //-----------
        //　モデルの表示
        //-----------

        int yMax = player.getYMax();
        if (yMax < 750) {
            canvasBaseX = 0;
        } else {
            canvasBaseY = yMax - 750;
        }

        drawPlayer(player, playerImageView);
        drawEntity(castle, castleImage, castleImageView);
        for (int i = 0; i < COIN_COUNTS; i++) {
            Coin coin = coins.get(i);
            ImageView coinImageView = coinImageViews.get(i);
            drawCoin(coin, coinImageView);
        }
        for (int i = 0; i < UFO_COUNTS; i++) {
            UFO ufo = ufos.get(i);
            ImageView ufoImageView = ufoImageViews.get(i);
            drawUFO(ufo, ufoImageView);
        }
        for (int i = 0; i < PLATFORM_COUNTS; i++) {
            Platform platform = platforms.get(i);
            ImageView platformImageView = platformImageViews.get(i);
            drawEntity(platform, platformImage, platformImageView);
        }
        for (int i = 0; i < MOVING_PLATFORM_COUNTS; i++) {
            MovingPlatform movingPlatform = movingPlatforms.get(i);
            ImageView movingPlatformImageView = movingPlatformImageViews.get(i);
            drawEntity(movingPlatform, platformImage, movingPlatformImageView);
        }
        for (int i = 0; i < BROKEN_PLATFORM_COUNTS; i++) {
            BrokenPlatform brokenPlatform = brokenPlatforms.get(i);
            ImageView brokenPlatformImageView = brokenPlatformImageViews.get(i);
            drawBrokenPlatform(brokenPlatform, brokenPlatformImageView);
        }
        for (int i = 0; i < SPRING_COUNTS; i++) {
            Spring spring = springs.get(i);
            ImageView springImageView = springImageViews.get(i);
            drawEntity(spring, springImage, springImageView);
        }

        if (player.isDead()) {
            gameEndTextView.setText("Game Over !!");
            gameEndTextView.setTextSize(32);
            gameEndTextView.setTextColor(Color.RED);
            gameEndTextView.setVisibility(TextView.VISIBLE);
            drawTextViewCenter(350, player.getYMax(), gameEndTextView);
        }
        if (player.isClear()) {
            gameEndTextView.setText("Game Clear !!");
            gameEndTextView.setTextSize(32);
            gameEndTextView.setTextColor(Color.BLUE);
            gameEndTextView.setVisibility(TextView.VISIBLE);
            drawTextViewCenter(350, player.getYMax(), gameEndTextView);
        }

        pointTextView.setText("" + player.getPoint());
        pointTextView.setTextSize(32);
        pointTextView.setTextColor(Color.BLUE);



        if (player.getYMax() + 650 < 1400) {
            drawTextViewRight(700, 1400, pointTextView);
        } else {
            drawTextViewRight(700, player.getYMax() + 650, pointTextView);
        }
    }

    private void drawEntity(Entity entity, Bitmap image, ImageView imageView) {
        drawImage(
                entity.getX(),
                entity.getY(),
                entity.getXSize(),
                entity.getYSize(),
                image,
                imageView
        );
    }

    private void drawPlayer(Player player, ImageView imageView) {
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
                imageView
        );
    }

    private void drawCoin(Coin coin, ImageView imageView) {
        Bitmap coinImage;
        switch (coin.getState()) {
            case 0:
                coinImage = coin1Image;
                break;
            case 1:
            case 3:
                coinImage = coin2Image;
                break;
            case 2:
                coinImage = coin3Image;
                break;
            case 10:
                imageView.setVisibility(ImageView.GONE);
            default:
                coinImage = coin1Image;

        }
        drawImage(
                coin.getX(),
                coin.getY(),
                coin.getXSize(),
                coin.getYSize(),
                coinImage,
                imageView
        );
    }

    private void drawUFO(UFO ufo, ImageView imageView) {
        Bitmap ufoImage;
        if (ufo.getVector()) {
            ufoImage = ufoRightImage;
        } else {
            ufoImage = ufoLeftImage;
        }
        drawImage(
                ufo.getX(),
                ufo.getY(),
                ufo.getXSize(),
                ufo.getYSize(),
                ufoImage,
                imageView
        );
    }

    private void drawBrokenPlatform(BrokenPlatform brokenPlatform, ImageView imageView) {
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
                imageView.setVisibility(ImageView.GONE);
            default:
                brokenPlatformImage = platformImage;
        }
        drawImage(
                brokenPlatform.getX(),
                brokenPlatform.getY(),
                brokenPlatform.getXSize(),
                brokenPlatform.getYSize(),
                brokenPlatformImage,
                imageView
        );
    }
}