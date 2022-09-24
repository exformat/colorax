package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Pools;

public class LinearVelocityComponent implements Component {
	
	public Vector2 value = Pools.obtain(Vector2.class);

	public void init(float x, float y){
		value.set(x, y);
	}
	
	public static ComponentMapper<LinearVelocityComponent> mapper = ComponentMapper.getFor(LinearVelocityComponent.class);
}
