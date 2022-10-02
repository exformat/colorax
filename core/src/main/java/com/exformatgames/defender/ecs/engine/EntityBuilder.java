package com.exformatgames.defender.ecs.engine;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;

public abstract class EntityBuilder {
	protected static World world;
	protected static PooledEngine engine;
	protected static TextureAtlas atlas;
	protected static OrthographicCamera camera;
	
	public static void set(World w, PooledEngine e, TextureAtlas a, OrthographicCamera c){
		EntityBuilder.world = w;
		EntityBuilder.engine = e;
		EntityBuilder.atlas = a;
		EntityBuilder.camera = c;
	}

	protected Entity entity;

	public EntityBuilder(){
		if (engine != null) {
			entity = engine.createEntity();
			engine.addEntity(entity);
		}
	}

	protected abstract void create();

	public static  <T extends Component> T createComponent (Entity entity, Class<T> componentType) {
		T type = engine.createComponent(componentType);
		entity.add(type);
		return type;
	}

	public <T extends Component> T createComponent (Class<T> componentType) {
		T type = engine.createComponent(componentType);
		entity.add(type);
		return type;
	}
}
