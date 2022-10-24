package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.*;
import com.badlogic.gdx.utils.*;

public class AnimationComponent implements Component {
	public Animation<AtlasRegion> animation;
	public float scale = 1;
	public float timeAnimation = 0;
	
	public void init(float frameTime, Array<AtlasRegion > regions, Animation.PlayMode mode, float scl){
		animation = new Animation<AtlasRegion>(frameTime, regions, mode);
		this.scale = scl;
	}
	
	public static ComponentMapper<AnimationComponent> mapper = ComponentMapper.getFor(AnimationComponent.class);
}
