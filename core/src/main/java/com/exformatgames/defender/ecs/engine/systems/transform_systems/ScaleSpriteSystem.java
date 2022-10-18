package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class ScaleSpriteSystem extends IteratingSystem {
	public ScaleSpriteSystem() {
		super(Family.all(ScaleComponent.class, SpriteComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		ScaleComponent scale = ScaleComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);

		for (SpriteComponent sprite: spriteComponent.spriteComponentArray) {
			if(scale.isTo){
				sprite.scaleX = scale.toX;
				sprite.scaleY = scale.toY;
			}
			else{
				sprite.scaleX += scale.byX;
				sprite.scaleY += scale.byY;
			}

			if(sprite.scaleX < 0 || sprite.scaleY < 0){
				sprite.scaleX = 0;
				sprite.scaleY = 0;
			}

			sprite.dirty = true;
		}

		entity.remove(ScaleComponent.class);
	}
}
