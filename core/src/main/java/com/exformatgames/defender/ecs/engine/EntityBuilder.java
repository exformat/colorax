package com.exformatgames.defender.ecs.engine;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;

public abstract class EntityBuilder {
	protected static World world;
	protected static PooledEngine engine;
	protected static TextureAtlas atlas;
	protected static OrthographicCamera camera;
	protected static AssetManager assetManager;

	protected static BodyDef BODY_DEF = new BodyDef();
	protected static FixtureDef FIXTURE_DEF = new FixtureDef();

	protected static CircleShape CIRCLE_SHAPE = new CircleShape();
	protected static PolygonShape POLYGON_SHAPE = new PolygonShape();
	protected static ChainShape CHAIN_SHAPE = new ChainShape();
	protected static EdgeShape EDGE_SHAPE = new EdgeShape();

	private static Vector2 zeroVector = new Vector2();
	
	public static void init(World w, PooledEngine e, TextureAtlas a, OrthographicCamera c, AssetManager am){
		EntityBuilder.world = w;
		EntityBuilder.engine = e;
		EntityBuilder.atlas = a;
		EntityBuilder.camera = c;
		EntityBuilder.assetManager = am;
	}

	protected Entity entity;

	public EntityBuilder(){
		if (engine != null) {
			entity = engine.createEntity();
			engine.addEntity(entity);
		}
	}

	public abstract void create();

	public static  <T extends Component> T createComponent (Entity entity, Class<T> componentType) {
		T type = engine.createComponent(componentType);

		if (type instanceof SpriteComponent){
			SpriteComponent owner = entity.getComponent(SpriteComponent.class);
			if (owner != null) {
				owner.spriteComponentArray.add((SpriteComponent) type);
			}
			else {
				((SpriteComponent) type).spriteComponentArray.add((SpriteComponent) type);
				entity.add(type);
			}
			return type;
		}

		entity.add(type);
		return type;
	}

	public <T extends Component> T createComponent (Class<T> componentType) {
		T type = engine.createComponent(componentType);

		if (type instanceof SpriteComponent){
			SpriteComponent owner = entity.getComponent(SpriteComponent.class);
			if (owner != null) {
				owner.spriteComponentArray.add((SpriteComponent) type);
			}
			else {
				((SpriteComponent) type).spriteComponentArray.add((SpriteComponent) type);
				entity.add(type);
			}
			return type;
		}

		entity.add(type);
		return type;
	}

	protected static void resetBodyDef(){
		BODY_DEF.type = BodyDef.BodyType.DynamicBody;
		BODY_DEF.gravityScale = 1;
		BODY_DEF.bullet = false;

		BODY_DEF.active = true;
		BODY_DEF.allowSleep = true;

		BODY_DEF.angle = 0;
		BODY_DEF.angularDamping = 0;
		BODY_DEF.angularVelocity = 0;
		BODY_DEF.fixedRotation = false;

		BODY_DEF.position.set(0, 0);
		BODY_DEF.linearDamping = 0;
		BODY_DEF.linearVelocity.set(0, 0);
	}
	protected static void resetFixtureDef(){
		FIXTURE_DEF.shape = null;
		FIXTURE_DEF.friction = 0.2f;

		FIXTURE_DEF.restitution = 0;
		FIXTURE_DEF.density = 0;
		FIXTURE_DEF.isSensor = false;
		FIXTURE_DEF.filter.categoryBits = 0x0001;
		FIXTURE_DEF.filter.groupIndex = 0;
		FIXTURE_DEF.filter.maskBits = -1;

	}
	protected static void resetShapes(){
		CIRCLE_SHAPE.setPosition(zeroVector);
		CIRCLE_SHAPE.setRadius(0);

		POLYGON_SHAPE.setAsBox(0, 0);

		CHAIN_SHAPE.clear();

		EDGE_SHAPE.set(zeroVector, zeroVector);
	}
	protected static void resetAll(){
		resetBodyDef();
		resetFixtureDef();
		resetShapes();
	}
}
