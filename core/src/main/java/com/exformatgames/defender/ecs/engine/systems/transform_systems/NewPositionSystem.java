package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.audio_components.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;
import com.exformatgames.defender.ecs.engine.components.light_components.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

import static com.badlogic.gdx.graphics.g2d.SpriteBatch.*;

public class NewPositionSystem extends IteratingSystem {

	public NewPositionSystem() {
		super(Family.all(NewPositionComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {

		NewPositionComponent position = NewPositionComponent.mapper.get(entity);

		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		ParticleEmitterComponent particleComponent = ParticleEmitterComponent.mapper.get(entity);
		B2DParticleEmitterComponent b2DParticleComponent = B2DParticleEmitterComponent.mapper.get(entity);
		PointSoundComponent pointSoundComponent = PointSoundComponent.mapper.get(entity);
		LightComponent lightComponent = LightComponent.mapper.get(entity);
		CameraComponent cameraComponent = CameraComponent.mapper.get(entity);
		PositionComponent positionComponent = PositionComponent.mapper.get(entity);

		if(positionComponent != null){
			positionComponent.init(position.x, position.y);
		}

		if(particleComponent != null){
			particleComponent.emitter.setPosition(position.x, position.y);
		}

		if(b2DParticleComponent != null){
			b2DParticleComponent.emitter.setPosition(position.x, position.y);
		}

		if(pointSoundComponent != null){
			pointSoundComponent.position.set(position.x, position.y);
		}

		if(lightComponent != null){
			lightComponent.light.setPosition(position.x, position.y);
		}

		if(cameraComponent != null){
			float z = cameraComponent.camera.position.z;
			cameraComponent.camera.position.set(position.x, position.y, z);
			cameraComponent.camera.update();
		}
		
		if(spriteComponent != null){
			for (SpriteComponent sprite: spriteComponent.spriteComponentArray) {
 				sprite.x = position.x;
				sprite.y = position.y;

				if (sprite.dirty){
					continue;
				}
				if (sprite.rotation != 0 || sprite.scaleX != 1 || sprite.scaleY != 1) {
					sprite.dirty = true;
					continue;
				}

				float x2 = position.x + sprite.width;
				float y2 = position.y + sprite.height;

				float[] vertices = sprite.vertices;

				vertices[X1] = position.x + sprite.offsetX;
				vertices[Y1] = position.y + sprite.offsetY;
				vertices[X2] = position.x + sprite.offsetX;
				vertices[Y2] = y2 + sprite.offsetY;
				vertices[X3] = x2 + sprite.offsetX;
				vertices[Y3] = y2 + sprite.offsetY;
				vertices[X4] = x2 + sprite.offsetX;
				vertices[Y4] = position.y + sprite.offsetY;
			}
		}

		entity.remove(NewPositionComponent.class);
	}
}
