package com.exformatgames.colorax.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.exformatgames.colorax.ColoraxCore;
import com.exformatgames.defender.ecs.test.TestCore;
import com.exformatgames.defender.ecs.utils.Particles;

public class TestGameScreen implements Screen {
    private Game game;


    private Viewport gameViewport;
    private SpriteBatch spriteBatch;
    private TextureAtlas textureAtlas;
    private AssetManager assetManager;
    private World box2d;
    private InputMultiplexer inputMultiplexer;


    private FPSLogger fpsLogger;

    private TestCore testCore;
    private ColoraxCore coloraxCore;

    public TestGameScreen(Game game) {
        this.game = game;
    }


    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera();

        gameViewport = new ExtendViewport(12.80f, 10.24f, 19.20f, 10.80f, camera);

        spriteBatch = new SpriteBatch();
        textureAtlas = new TextureAtlas();
        assetManager = new AssetManager();
        box2d = new World(new Vector2(0, 0), true);
        inputMultiplexer = new InputMultiplexer();

        AssetDescriptor<com.badlogic.gdx.graphics.g2d.TextureAtlas> atlasAssetDescriptor = new AssetDescriptor<>("atlas/colorax.atlas", com.badlogic.gdx.graphics.g2d.TextureAtlas.class);




        assetManager.load("particles/bullet_track.part", ParticleEffect.class);
        assetManager.load(atlasAssetDescriptor);
        assetManager.load("audio/sound/sound_turret_shot.ogg", Sound.class);
        assetManager.load("audio/sound/sound_explosion_bullet.ogg", Sound.class);
        assetManager.load("audio/sound/sound_tank_engine.ogg", Sound.class);

        while (! assetManager.isFinished()){
            assetManager.update();
            //System.out.println(assetManager.getProgress());
        }

        ParticleEffect particleEffect = assetManager.get("particles/bullet_track.part");
        particleEffect.scaleEffect(0.01f);
        particleEffect.getEmitters().removeIndex(0);

        Particles.PUT("bullet_track", new ParticleEffectPool(particleEffect, 10, 100));

        textureAtlas = assetManager.get(atlasAssetDescriptor);
        textureAtlas.addRegion("test_region", new TextureRegion(new Texture("test_texture.png")));

        coloraxCore = new ColoraxCore(camera, box2d,
                spriteBatch, inputMultiplexer,
                textureAtlas, assetManager);
        coloraxCore.create(true, false);

        fpsLogger = new FPSLogger();

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.DARK_GRAY);

        coloraxCore.update(delta);

        //fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        gameViewport.apply(true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        coloraxCore.dispose();
    }
}
