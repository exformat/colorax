package com.exformatgames.defender.ecs.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.exformatgames.defender.ecs.engine.DefenderCore;

public class TestCore extends DefenderCore {

    public TestCore() {
        super(new OrthographicCamera(640, 480), new World(new Vector2(0, 0), true), new SpriteBatch(), new InputMultiplexer(), new TextureAtlas(), null);

        atlas.addRegion("testRegion", new TextureRegion(new Texture("test_texture.png")));
    }

    @Override
    protected void initEntities() {

        Gdx.input.setInputProcessor(getInputMultiplexer());

        for (int i = 0; i < 1; i++) {
            new TestEntity().create();
        }
    }

    @Override
    protected void initGameSystems() {
        engine.addSystem(new TestSystem());
    }
}
