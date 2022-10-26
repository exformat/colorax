package com.exformatgames.defender.ecs.engine.components.audio_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class PointSoundComponent implements Component {
	public Vector2 position = new Vector2();
	public float hearingRadius = 1;


	public PointSoundComponent init(Vector2 position, float hearingRadius) {
		this.position.set(position);
		this.hearingRadius = hearingRadius;

		return this;
	}

	public PointSoundComponent init(float x, float y, float hearingRadius) {
		this.position.set(x, y);
		this.hearingRadius = hearingRadius;

		return this;
	}
	public static final ComponentMapper<PointSoundComponent> mapper = ComponentMapper.getFor(PointSoundComponent.class);
}
