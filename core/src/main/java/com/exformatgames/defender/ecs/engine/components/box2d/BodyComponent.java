package com.exformatgames.defender.ecs.engine.components.box2d;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class BodyComponent implements Component {
	public Body body = null;
	
	public Vector2 oldPosition = new Vector2();
	public float oldRotation = 0;

	public BodyComponent init(Body body){
		this.body = body;
		oldPosition.set(body.getPosition());
		oldRotation = body.getAngle();

		return this;
	}

	public BodyComponent setDamping(float linear, float angular){
		body.setLinearDamping(linear);
		body.setAngularDamping(angular);
		return this;
	}

	public BodyComponent setFixedRotation(boolean flag){

		body.setFixedRotation(flag);

		return this;
	}

	public BodyComponent setUserData(Object obj) {

		body.setUserData(obj);

		return this;
	}

	public static ComponentMapper<BodyComponent> mapper = ComponentMapper.getFor(BodyComponent.class);
}
