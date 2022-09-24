package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;

public class PositionComponent implements Component {
	public float x = 0;
	public float y = 0;

	public PositionComponent init(float x, float y){
		this.x = x;
		this.y = y;

		return this;
	}
	
	public static ComponentMapper<PositionComponent> mapper = ComponentMapper.getFor(PositionComponent.class);
}
