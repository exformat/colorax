package com.exformatgames.colorax;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import com.exformatgames.colorax.entities.TankEntityBuilder;
import com.exformatgames.colorax.game_systems.RotateTankSystem;
import com.exformatgames.defender.ecs.engine.DefenderCore;

public class ColoraxCore extends DefenderCore {

    public ColoraxCore(OrthographicCamera camera, World box2DWorld, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, TextureAtlas atlas, AssetManager assetManager) {
        super(camera, box2DWorld, spriteBatch, inputMultiplexer, atlas, assetManager);
    }

    @Override
    protected void initEntities() {
        new TankEntityBuilder().create();
    }

    @Override
    protected void initGameSystems() {
        engine.addSystem(new RotateTankSystem());
    }
}
