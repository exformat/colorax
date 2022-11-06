package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class AnimationSpriteSystem extends IteratingSystem {
	
	public AnimationSpriteSystem(){
		super(Family.all(AnimationComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);

		for (int i = 0; i < animationComponent.animationComponentArray.size; i++) {
			AnimationComponent anim = animationComponent.animationComponentArray.get(i);
			SpriteComponent sprite = spriteComponent.spriteComponentArray.get(i);

			anim.timeAnimation += dt;

			if(anim.animation.getPlayMode() == Animation.PlayMode.LOOP || ! anim.animation.isAnimationFinished(anim.timeAnimation)){
				sprite.setUV(anim.animation.getKeyFrame(anim.timeAnimation));
			}
		}
	}
}
