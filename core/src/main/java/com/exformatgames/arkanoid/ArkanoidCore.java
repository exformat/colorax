package com.exformatgames.arkanoid;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.exformatgames.arkanoid.entities.B2DWorldEntityBuilder;
import com.exformatgames.arkanoid.entities.BallEntityBuilder;
import com.exformatgames.defender.ecs.engine.DefenderCore;

public class ArkanoidCore extends DefenderCore {

    public ArkanoidCore(OrthographicCamera camera, World box2DWorld, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, TextureAtlas atlas, AssetManager assetManager) {
        super(camera, box2DWorld, spriteBatch, inputMultiplexer, atlas, assetManager);
    }

    @Override
    protected void initEntities() {
        new B2DWorldEntityBuilder().create();
        BallEntityBuilder.create(new Vector2(5, 5), new Vector2(0, -1), 0.1f);
    }

    @Override
    protected void initGameSystems() {

    }
}
