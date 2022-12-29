package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.light_components.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.ecs.engine.systems.util_system.MultiThreadSystem;

public class RotationSystem extends IteratingSystem {
	
	public RotationSystem(){
		super(Family.all(RotationComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		RotationComponent rotation = ComponentMapper.getFor(RotationComponent.class).get(entity);//RotationComponent.mapper.get(entity);
		
		SpriteComponent spriteComponent = ComponentMapper.getFor(SpriteComponent.class).get(entity);//SpriteComponent.mapper.get(entity);
		LightComponent lightComponent = ComponentMapper.getFor(LightComponent.class).get(entity);//LightComponent.mapper.get(entity);

		if(lightComponent != null){
			lightComponent.light.setDirection(rotation.degress);
		}

		if(spriteComponent != null){
			for (SpriteComponent sprite: spriteComponent.spriteComponentArray) {
				sprite.rotation = rotation.degress;
				sprite.dirty = true;
			}
		}
		
		entity.remove(RotationComponent.class);
	}
}
