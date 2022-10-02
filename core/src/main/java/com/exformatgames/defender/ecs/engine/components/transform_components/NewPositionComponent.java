package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;

public class NewPositionComponent implements Component {
	public float x = 0;
	public float y = 0;

	public NewPositionComponent init(float x, float y){
		this.x = x;
		this.y = y;

		return this;
	}
	
	public static ComponentMapper<NewPositionComponent> mapper = ComponentMapper.getFor(NewPositionComponent.class);
}
