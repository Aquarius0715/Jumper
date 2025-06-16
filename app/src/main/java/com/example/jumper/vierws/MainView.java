package com.example.jumper.vierws;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.jumper.MainActivity;
import com.example.jumper.R;
import com.example.jumper.helpers.BaseView;
import com.example.jumper.models.BrokenPlatform;
import com.example.jumper.models.Castle;
import com.example.jumper.models.Coin;
import com.example.jumper.models.Entity;
import com.example.jumper.models.MovingPlatform;
import com.example.jumper.models.Platform;
import com.example.jumper.models.Player;
import com.example.jumper.models.Spring;
import com.example.jumper.models.UFO;
import com.example.jumper.models.World;

import java.util.LinkedList;

public class MainView extends BaseView {

    ConstraintLayout constraintLayout;
    Context context;
    MainActivity mainActivity;

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
    ImageViewBuilder imageViewBuilder;

    private TextView gameEndTextView;
    private TextView gameClearTextView;
    private TextView pointTextView;

    Button retryButton;

    public MainView(Context context) {
        super(context);
        this.context = context;
        this.mainActivity=(MainActivity)context;
        this.constraintLayout = baseActivity.findViewById(R.id.main);

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
        imageViewBuilder = new ImageViewBuilder(constraintLayout, context);

        gameEndTextView = new TextView(context);
        gameClearTextView = new TextView(context);
        pointTextView = new TextView(context);

        constraintLayout.addView(gameEndTextView);
        constraintLayout.addView(gameClearTextView);
        constraintLayout.addView(pointTextView);

        gameEndTextView.setVisibility(TextView.GONE);
        gameClearTextView.setVisibility(TextView.GONE);

        retryButton = new Button(context);
        constraintLayout.addView(retryButton);
        retryButton.setVisibility(Button.GONE);
        retryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.retry();
            }
        });

    }

    public void draw(World world) {

        imageViewBuilder.reset();


        Player player = world.getPlayer();

        int yMax = player.getYMax();
        if (yMax < 750) {
            canvasBaseY = 0;
        } else {
            canvasBaseY = yMax - 750;
        }


        for (Entity entity : world.getAllEntities()) {
            if (entity instanceof  Player) {
                drawPlayer((Player) entity);
            } else if (entity instanceof Castle) {
                drawEntity(entity, castleImage);
            } else if (entity instanceof UFO) {
                drawUFO((UFO) entity);
            } else if (entity instanceof Coin) {
                drawCoin((Coin) entity);
            } else if (entity instanceof Spring) {
                drawEntity(entity, springImage);
            } else if (entity instanceof BrokenPlatform) {
                drawBrokenPlatform((BrokenPlatform) entity);
            } else if (entity instanceof MovingPlatform) {
                drawEntity(entity, platformImage);
            } else if (entity instanceof Platform) {
                drawEntity(entity, platformImage);
            }
        }

        int score = player.getPoint();
        drawScore(score, player);

        if (player.isDead()) {
            drawGameOver();
            drawRetryButton();
        } else if (player.isClear()) {
            drawGameClear();
            drawRetryButton();
        } else {
            gameEndTextView.setVisibility(GONE);
            eraseRetryButton();
        }
    }

    //======================
    // テキスト表示用の関数
    //======================
    private void drawScore(int score, Player player) {
        pointTextView.setText("" + score);
        pointTextView.setTextColor(Color.BLUE);
        pointTextView.setTextSize(32);
        if (player.getYMax() + 650 < 1400) {
            drawTextViewRight(700, 1400, pointTextView);
        } else {
            drawTextViewRight(700, player.getYMax() + 650, pointTextView);
        }    }

    private void drawGameOver() {
        gameEndTextView.setText("Game Over !!");
        gameEndTextView.setTextSize(32);
        gameEndTextView.setTextColor(Color.RED);
        gameEndTextView.setVisibility(TextView.VISIBLE);
        drawTextViewCenter(350, canvasBaseY + 750, gameEndTextView);
    }

    private void drawGameClear() {
        gameEndTextView.setText("Game Clear !!");
        gameEndTextView.setTextSize(32);
        gameEndTextView.setTextColor(Color.BLUE);
        gameEndTextView.setVisibility(TextView.VISIBLE);
        drawTextViewCenter(350, canvasBaseY + 750, gameEndTextView);
    }

    public void drawRetryButton() {
        retryButton.setVisibility(View.VISIBLE);
        retryButton.setTextSize(24);
        retryButton.setText("もう一回！");
        drawViewCenter(350, canvasBaseY + 100, retryButton);
    }

    public void eraseRetryButton() {
        retryButton.setVisibility(View.GONE);
    }


    //======================
    // キャラクター表示用の関数
    //======================
    private void drawEntity(Entity entity, Bitmap image) {
        ImageView imageView = imageViewBuilder.getImageView();
        drawImage(
                entity.getX(),
                entity.getY(),
                entity.getXSize(),
                entity.getYSize(),
                image,
                imageView
        );
    }

    private void drawPlayer(Player player) {
        if (player.getXSpeed() >= 0) {
            drawEntity(player, playerRightImage);
        } else {
            drawEntity(player, playerLeftImage);
        }
    }

    private void drawCoin(Coin coin) {
        switch (coin.getState()) {
            case 0:
                drawEntity(coin, coin1Image);
                break;
            case 1:
            case 3:
                drawEntity(coin, coin2Image);
                break;
            case 2:
                drawEntity(coin, coin3Image);
                break;
        }
    }

    private void drawUFO(UFO ufo) {
        if (ufo.getVector()) {
            drawEntity(ufo, ufoRightImage);
        } else {
            drawEntity(ufo, ufoLeftImage);
        }
    }

    private void drawBrokenPlatform(BrokenPlatform brokenPlatform) {
        switch (brokenPlatform.getState()) {
            case 0:
                drawEntity(brokenPlatform, platformImage);
                break;
            case 1:
                drawEntity(brokenPlatform, platform1Image);
                break;
            case 2:
                drawEntity(brokenPlatform, platform2Image);
                break;
            case 3:
                drawEntity(brokenPlatform, platform3Image);
                break;
        }
    }
}

