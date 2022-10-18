package com.exformatgames.colorax.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.exformatgames.colorax.ColoraxCore;
import com.exformatgames.defender.ecs.test.TestCore;

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

        gameViewport = new ExtendViewport(1280, 1024, 1920, 1080, camera);

        spriteBatch = new SpriteBatch();
        textureAtlas = new TextureAtlas();
        assetManager = new AssetManager();
        box2d = new World(new Vector2(0, 0), true);
        inputMultiplexer = new InputMultiplexer();

        Texture baseTank = new Texture("artwork/tank body_teal.png");
        Texture turretTank = new Texture("artwork/tank turret_teal.png");

        textureAtlas.addRegion("body", new TextureRegion(baseTank));
        textureAtlas.addRegion("turret", new TextureRegion(turretTank));
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
        ScreenUtils.clear(0,0,0,1);

        coloraxCore.update(delta);
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
