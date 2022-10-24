package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.light_components.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class RotateSystem extends IteratingSystem {
	
	public RotateSystem(){
		super(Family.all(RotateComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		RotateComponent rotate = RotateComponent.mapper.get(entity);
		
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		LightComponent lightComponent = LightComponent.mapper.get(entity);
		CameraComponent cameraComponent = CameraComponent.mapper.get(entity);

		if(lightComponent != null){
			float angle = lightComponent.light.getDirection() + rotate.degres;
			lightComponent.light.setDirection(angle);
		}

		if(cameraComponent != null){
			cameraComponent.camera.rotate(rotate.degres);
			cameraComponent.camera.update();
		}
		
		if(spriteComponent != null){
			for (SpriteComponent sprite: spriteComponent.spriteComponentArray) {
				sprite.rotation += rotate.degres;
				sprite.dirty = true;
			}

		}

		entity.remove(RotateComponent.class);
	}
}
