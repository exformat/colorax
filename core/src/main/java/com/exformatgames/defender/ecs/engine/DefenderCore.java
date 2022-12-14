package com.exformatgames.defender.ecs.engine;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.physics.box2d.*;
import com.exformatgames.defender.ecs.engine.systems.audio_systems.*;
import com.exformatgames.defender.ecs.engine.systems.box2d_systems.*;
import com.exformatgames.defender.ecs.engine.systems.debug.*;
import com.exformatgames.defender.ecs.engine.systems.input_systems.*;
import com.exformatgames.defender.ecs.engine.systems.rendering_systems.*;
import com.exformatgames.defender.ecs.engine.systems.transform_systems.*;
import com.exformatgames.defender.ecs.engine.systems.util_system.*;
import com.exformatgames.defender.ecs.utils.B2DContactListener;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public abstract class DefenderCore {
	
	protected OrthographicCamera camera;
	protected World box2DWorld;
	protected SpriteBatch spriteBatch;
	protected InputMultiplexer inputMultiplexer;
	protected TextureAtlas atlas;
	protected AssetManager assetManager;

	protected PooledEngine engine;

	private boolean stopEngine = false;
	private float engineDeltaTime = 0;

	public DefenderCore(OrthographicCamera camera, World box2DWorld, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, TextureAtlas atlas, AssetManager assetManager) {
		this.camera = camera;
		this.box2DWorld = box2DWorld;
		this.spriteBatch = spriteBatch;
		this.inputMultiplexer = inputMultiplexer;
		this.atlas = atlas;
		this.assetManager = assetManager;
		
		engine = new PooledEngine(50, 500, 500, 5000);
	}
	
	protected abstract void initEntities();
	protected abstract void initGameSystems();
	
	public void update(float deltaTime){
		engine.update(deltaTime);
	}
	
	public final void create(boolean isDebug, boolean asyncEngine){
		EntityBuilder.init(box2DWorld, engine, atlas, camera, assetManager);
		BodyBuilder.init(box2DWorld);

		initEntities();//abstract
		
		initInputSystems();
		
		initGameSystems();//abstract
		
		if(box2DWorld != null){
			initBox2DSystems();
		}
		
		initAudioSystems();
		engine.addSystem(new VibrationSystem());//ok
		initAnimationEffectSystems();
		initTransformSystems();
		initParticleSystems();

		initRenderSystems(asyncEngine);

		initUtilsSystems();
		
		if(isDebug && ! asyncEngine)
			initDebugSystems();
		
		engine.addSystem(new ResetGestureInputSystem()); //ok
		engine.addSystem(new ExitSystem()); //ok
		engine.addSystem(new DeleteEntitySystem(box2DWorld));//ok

		if (asyncEngine){
			new Thread(() -> {
				while (!stopEngine){
					long start = System.nanoTime();
					update(engineDeltaTime);
					engineDeltaTime = (System.nanoTime() - start) / 1000_000_000f;
				}
			}).start();
		}
	}
	
	private void initInputSystems(){
		engine.addSystem(new GestureInputSystem(inputMultiplexer));//ok
		engine.addSystem(new KeyboardInputSystem());//ok
		engine.addSystem(new MouseInputSystem(camera));
	}
	
	private void initBox2DSystems(){
		engine.addSystem(new TransformBodySystem()); //ok
		engine.addSystem(new ForceSystem()); //ok
		engine.addSystem(new AngularImpulseSystem()); //ok
		engine.addSystem(new LinearImpulseSystem());
		engine.addSystem(new RemoveBodySystem(box2DWorld)); //ok
		engine.addSystem(new RayCastSystem(box2DWorld)); //ok
		engine.addSystem(new UpdateTransformSystem());//ok
		engine.addSystem(new CollisionClearSystem()); //ok
		engine.addSystem(new UpdateWorldSystem());//ok
		engine.addSystem(new AABBQuerySystem(box2DWorld));

		box2DWorld.setContactListener(new B2DContactListener());
	}
	
	private void initAudioSystems(){
		engine.addSystem(new SoundSystem()); // ok
		engine.addSystem(new PointSoundSystem(camera)); // ok
		engine.addSystem(new MusicSystem()); // ok
	}
	
	private void initAnimationEffectSystems(){
		engine.addSystem(new AnimationSpriteSystem()); // ok
		engine.addSystem(new ParallaxSystem(camera)); // ok
		engine.addSystem(new ScrollSystem(camera)); // ok
		engine.addSystem(new AngularVelocitySystem()); // ok
		engine.addSystem(new LinearVelocitySystem()); // ok
	}
	
	private void initTransformSystems(){
		engine.addSystem(new RotationSystem()); // ok
		engine.addSystem(new RotateSystem()); // ok
		engine.addSystem(new ScaleLoopSystem());
		engine.addSystem(new ScaleSpriteSystem()); // ok
		engine.addSystem(new NewPositionSystem()); // ok
		engine.addSystem(new TranslateSystem()); // ok
	}
	
	private void initRenderSystems(boolean asyncRender){
		engine.addSystem(new OrthogonalMapRenderSystem(camera));
		engine.addSystem(new CullingSystem(camera)); //ok

		if (asyncRender) {
			engine.addSystem(new AsyncSortRenderSystem(camera, spriteBatch)); //ok
		}
		else {
			engine.addSystem(new SortedRenderSystem(camera, spriteBatch)); //ok
		}
	}
	
	private void initParticleSystems(){
		engine.addSystem(new ParticlesSystem());
		engine.addSystem(new B2DParticleEmmiterUpateSystem());
	}
	
	private void initUtilsSystems(){
		engine.addSystem(new PreferencesSystem()); //TODO not work!!!
	}
	
	private void initDebugSystems(){
		ShapeRenderer shapeRenderer = new ShapeRenderer();

		if(box2DWorld != null){
			engine.addSystem(new DebugRayCastSystem(camera, shapeRenderer)); //ok
			engine.addSystem(new DebugPhysicsSystem(camera)); //ok
		}
		engine.addSystem(new DebugSpriteSystem(camera, shapeRenderer)); //ok
		engine.addSystem(new DebugShapesSystem(camera, shapeRenderer));
	}

	public PooledEngine getEngine() {
		return engine;
	}

	public InputMultiplexer getInputMultiplexer() {
		return inputMultiplexer;
	}

	public void dispose(){

		stopEngine = true;

		atlas.dispose();
		if (box2DWorld != null) {
			box2DWorld.dispose();
		}
		assetManager.dispose();
	}
}
