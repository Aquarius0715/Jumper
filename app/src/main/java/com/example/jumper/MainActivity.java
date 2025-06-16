package com.example.jumper;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.jumper.helpers.BaseActivity;
import com.example.jumper.models.BrokenPlatform;
import com.example.jumper.models.Coin;
import com.example.jumper.models.Entity;
import com.example.jumper.models.MovingPlatform;
import com.example.jumper.models.OnOverlapListener;
import com.example.jumper.models.Platform;
import com.example.jumper.models.Player;
import com.example.jumper.models.Spring;
import com.example.jumper.models.UFO;
import com.example.jumper.models.World;
import com.example.jumper.vierws.MainView;

import java.util.LinkedList;

public class MainActivity extends BaseActivity {


    // View
    MainView mainView;

    // Model
    private World world;

    //model
    int platform_sound;
    int coin_sound;
    int ufo_sound;
    int spring_sound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        gravityEnabled = true;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        // generate model instance
        world = new World();

        // generate view instance
        mainView = new MainView(this);

        // BGM
        loadMusic(R.raw.bgm);
        setMusicVolume(0.5f);
        startMusic();

        //effect sounds
        platform_sound = loadSound(R.raw.platform);
        coin_sound = loadSound(R.raw.coin);
        ufo_sound = loadSound(R.raw.ufo);
        spring_sound = loadSound(R.raw.spring);
        connectEffectSound(world);
    }


    @Override
    public void update() {

        //-----------
        // モデルへの更新
        //-----------
        int accelX = (int) accelerationController.x;
        world.getPlayer().setXSpeed(accelX * -1);

        world.move();
        //-----------
        //　モデルの表示
        //-----------
        mainView.draw(world);

    }

    public void retry() {
        stopMusic();
        world = new World();
        connectEffectSound(world);

        loadMusic(R.raw.bgm);
        setMusicVolume(0.5f);
        startMusic();
    }

    public void connectEffectSound(World world) {
        world.getAllEntities().stream()
                .filter(x -> x instanceof Platform)
                .forEach(x -> x.setOnOverlapListener(new PlatformOnOverlapListener()));
        world.getAllEntities().stream()
                .filter(x -> x instanceof MovingPlatform)
                .forEach(x -> x.setOnOverlapListener(new PlatformOnOverlapListener()));
        world.getAllEntities().stream()
                .filter(x -> x instanceof BrokenPlatform)
                .forEach(x -> x.setOnOverlapListener(new PlatformOnOverlapListener()));
        world.getAllEntities().stream()
                .filter(x -> x instanceof Coin)
                .forEach(x -> x.setOnOverlapListener(new CoinOnOverlapListener()));
        world.getAllEntities().stream()
                .filter(x -> x instanceof UFO)
                .forEach(x -> x.setOnOverlapListener(new UfoOnOverlapListener()));
        world.getAllEntities().stream()
                .filter(x -> x instanceof Spring)
                .forEach(x -> x.setOnOverlapListener(new SpringOnOverlapListener()));
    }

    public class PlatformOnOverlapListener implements OnOverlapListener {
        @Override
        public void overlap() {
            playSound(platform_sound, 1.0f);
        }
    }

    public class CoinOnOverlapListener implements OnOverlapListener {
        @Override
        public void overlap() {
            playSound(coin_sound, 1.0f);
        }
    }

    public class UfoOnOverlapListener implements OnOverlapListener {

        @Override
        public void overlap() {
            playSound(ufo_sound, 1.0f);
        }
    }

    public class SpringOnOverlapListener implements OnOverlapListener {

        @Override
        public void overlap() {
            playSound(spring_sound, 1.0f);
        }
    }




}