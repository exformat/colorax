package com.exformatgames.defender.ecs.engine.components.box2d;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class BodyComponent implements Component {
	public Body body = null;
	
	public Vector2 oldPosition = new Vector2();
	public float oldRotation = 0;

	public Body init(Body body){
		this.body = body;
		oldPosition.set(body.getPosition());
		oldRotation = body.getAngle();

		return body;
	}
	
	public static ComponentMapper<BodyComponent> mapper = ComponentMapper.getFor(BodyComponent.class);
}
