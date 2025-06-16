package com.example.jumper.models;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class World {
    public final int PLATFORM_COUNTS = 5;
    public final int MOVING_PLATFORM_COUNTS = 5;
    public final int BROKEN_PLATFORM_COUNTS = 5;
    public final int UFO_COUNTS = 6;
    public final int COIN_COUNTS = 7;
    public final int SPRING_COUNTS = 3;

    //-----------
    // Models
    //-----------
    private Player player;
    private Castle castle;
    private List<UFO> ufos;
    private List<Coin> coins;
    private List<Platform> platforms;
    private List<MovingPlatform> movingPlatforms;
    private List<BrokenPlatform> brokenPlatforms;
    private List<Spring> springs;

    private List<Entity> allEntities;

    public World() {
        //-----------
        // Create Player Instance
        //-----------
        player = new Player();
        castle = new Castle();
        coins = new LinkedList<>();
        ufos = new LinkedList<>();
        platforms = new LinkedList<>();
        movingPlatforms = new LinkedList<>();
        brokenPlatforms = new LinkedList<>();
        springs = new LinkedList<>();

        coins = IntStream.range(0, COIN_COUNTS)
                .map(n -> 450 + n * 800)
                .mapToObj(Coin::new)
                .collect(Collectors.toList());
        ufos = IntStream.range(0, UFO_COUNTS)
                .map(n -> 1100 + n * 1100)
                .mapToObj(UFO::new)
                .collect(Collectors.toList());
        platforms = IntStream.range(0, PLATFORM_COUNTS)
                .map(n -> 400 + n * 1200)
                .mapToObj(Platform::new)
                .collect(Collectors.toList());
        springs = IntStream.range(0, SPRING_COUNTS)
                .map(n -> 1800 + n * 1800)
                .mapToObj(Spring::new)
                .collect(Collectors.toList());
        movingPlatforms = IntStream.range(0, MOVING_PLATFORM_COUNTS)
                .map(n -> 800 + n * 1200)
                .mapToObj(MovingPlatform::new)
                .collect(Collectors.toList());
        brokenPlatforms = IntStream.range(0, BROKEN_PLATFORM_COUNTS)
                .map(n -> 1200 + n * 1200)
                .mapToObj(BrokenPlatform::new)
                .collect(Collectors.toList());

        allEntities = new LinkedList<>();
        allEntities.add(player);
        allEntities.add(castle);
        allEntities.addAll(platforms);
        allEntities.addAll(movingPlatforms);
        allEntities.addAll(brokenPlatforms);
        allEntities.addAll(ufos);
        allEntities.addAll(coins);
        allEntities.addAll(springs);


        castle.setPlayer(player);
        coins.forEach(x -> x.setPlayer(player));
        ufos.forEach(x -> x.setPlayer(player));
        platforms.forEach(x -> x.setPlayer(player));
        movingPlatforms.forEach(x -> x.setPlayer(player));
        brokenPlatforms.forEach(x -> x.setPlayer(player));
        springs.forEach(x -> x.setPlayer(player));
    }

    public void move() {
        //-----------
        // モデルの更新
        //-----------
        allEntities.forEach(Entity::move);
    }

    public List<Entity> getAllEntities() {
        return allEntities;
    }

    public Player getPlayer() {
        return player;
    }
}
