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

	public BodyComponent setFriction(float value){
		body.getFixtureList().first().setFriction(value);

		return this;
	}

	public BodyComponent setFriction(int indexFixture, float value){
		body.getFixtureList().get(indexFixture).setFriction(value);

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

	public BodyComponent setFilter(Filter filter){
		for (Fixture fixture: body.getFixtureList()) {
			fixture.setFilterData(filter);
		}

		return this;
	}

	public BodyComponent setFilter(Filter filter, int fixtureIndex){
		body.getFixtureList().get(fixtureIndex).setFilterData(filter);

		return this;
	}

	public BodyComponent setFilter(short mask, short category, short group, int fixtureIndex){
		Filter filter = new Filter();
		filter.maskBits = mask;
		filter.categoryBits = category;
		filter.groupIndex = group;

		setFilter(filter, fixtureIndex);

		return this;
	}

	public BodyComponent setFilter(short mask, short category, short group){
		Filter filter = new Filter();
		filter.maskBits = mask;
		filter.categoryBits = category;
		filter.groupIndex = group;

		setFilter(filter);

		return this;
	}

	public BodyComponent setFilter(short mask, short category){

		setFilter(mask, category, (short) 0);

		return this;
	}

	public BodyComponent setBullet(boolean flag) {
		body.setBullet(flag);

		return this;
	}

	public static ComponentMapper<BodyComponent> mapper = ComponentMapper.getFor(BodyComponent.class);
}
