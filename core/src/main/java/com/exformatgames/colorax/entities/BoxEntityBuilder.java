package com.exformatgames.colorax.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.light_components.LightComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.SizeComponent;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public class BoxEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(SizeComponent.class).init(1, 1);
        createComponent(BodyComponent.class).init(BodyBuilder.buildCircle(BodyDef.BodyType.DynamicBody, new Vector2(MathUtils.random(10f), MathUtils.random(10f)), 0.1f))
                .setDamping(5, 5)
                .setUserData(entity);
        createComponent(LightComponent.class).init(LightComponent.LightType.POINT, new Vector2(), Color.FOREST, 0, 100, 1, 0).light.attachToBody(BodyComponent.mapper.get(entity).body);
    }

    public void create(Vector2 position) {
        createComponent(SizeComponent.class).init(1, 1);
        createComponent(BodyComponent.class).init(BodyBuilder.buildCircle(BodyDef.BodyType.StaticBody, position, 0.1f));
        createComponent(LightComponent.class).init(LightComponent.LightType.POINT, position, Color.FOREST, 0, 100, 100, 0).light.attachToBody(BodyComponent.mapper.get(entity).body);
    }
}
