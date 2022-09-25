package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;

public class ScaleComponent implements Component {
	public float toX = 1;
	public float toY = 1;
	public float byX = 0;
	public float byY = 0;
	public boolean isTo = true;
	
	public static final ComponentMapper<ScaleComponent> mapper = ComponentMapper.getFor(ScaleComponent.class);
}
