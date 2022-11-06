package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.*;
import com.badlogic.gdx.utils.*;

public class AnimationComponent implements Component {

	public String name = "";
	public Array<AnimationComponent> animationComponentArray = new Array<>();
	public Animation<AtlasRegion> animation;
	public float scale = 1;
	public float timeAnimation = 0;
	
	public void init(String name, float frameDuration, Array<AtlasRegion > regions, Animation.PlayMode mode, float scl){
		animation = new Animation<>(frameDuration, regions, mode);

		this.scale = scl;
		timeAnimation = 0;
		this.name = name;
	}

	public void init(String name, float frameDuration, Animation<AtlasRegion> animation, Animation.PlayMode mode, float scl){
		this.animation = animation;
		this.animation.setPlayMode(mode);
		this.animation.setFrameDuration(frameDuration);
		this.scale = scl;
		timeAnimation = 0;
		this.name = name;
	}
	
	public static ComponentMapper<AnimationComponent> mapper = ComponentMapper.getFor(AnimationComponent.class);
}
