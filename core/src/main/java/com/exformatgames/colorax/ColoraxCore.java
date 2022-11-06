package com.exformatgames.colorax;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.exformatgames.colorax.entities.B2DWorldEntityBuilder;
import com.exformatgames.colorax.entities.TankEntityBuilder;
import com.exformatgames.colorax.entities.enemies.TankEnemyBuilder;
import com.exformatgames.colorax.entities.enemies.WaveEnemyEntityBuilder;
import com.exformatgames.colorax.game_systems.*;
import com.exformatgames.colorax.game_systems.enemy_systems.EnemySpawnSystem;
import com.exformatgames.colorax.game_systems.enemy_systems.EnemySystem;
import com.exformatgames.colorax.game_systems.enemy_systems.TankEnemySystem;
import com.exformatgames.colorax.game_systems.weapon_systems.WeaponSystem;
import com.exformatgames.defender.ecs.engine.DefenderCore;

public class ColoraxCore extends DefenderCore {

    public ColoraxCore(OrthographicCamera camera, World box2DWorld, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, TextureAtlas atlas, AssetManager assetManager) {
        super(camera, box2DWorld, spriteBatch, inputMultiplexer, atlas, assetManager);
    }

    @Override
    protected void initEntities() {
        new B2DWorldEntityBuilder().create();

        new WaveEnemyEntityBuilder().create(WaveEnemyEntityBuilder.TypeEnemy.TROOPER, new Vector2().setToRandomDirection().scl(5), 20, 2);
        new WaveEnemyEntityBuilder().create(WaveEnemyEntityBuilder.TypeEnemy.SOLDIER, new Vector2().setToRandomDirection().scl(6), 20, 4);
        new WaveEnemyEntityBuilder().create(WaveEnemyEntityBuilder.TypeEnemy.HEAVY, new Vector2().setToRandomDirection().scl(7), 20, 8);
        System.out.println("init soldiers");

        new TankEnemyBuilder().create(2, 2);
        System.out.println("init tank");

        new TankEntityBuilder().create();
        System.out.println("init player");
    }

    @Override
    protected void initGameSystems() {
        engine.addSystem(new EnemySpawnSystem());
        engine.addSystem(new EnemySystem());
        engine.addSystem(new TankEnemySystem());
        engine.addSystem(new WeaponSystem());

        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new RotateTankSystem());
        engine.addSystem(new BulletContactSystem());
        engine.addSystem(new ExplosionSystem());

        engine.addSystem(new DamageSystem());
        engine.addSystem(new DeathSystem());
    }
}
