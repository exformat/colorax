package com.exformatgames.arkanoid.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.arkanoid.components.BallComponent;
import com.exformatgames.defender.Constants;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.LinearImpulseComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.SizeComponent;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public class BallEntityBuilder extends EntityBuilder {

    @Override
    public void create() {}

    public static void create(Vector2 position, Vector2 direction, float impulse) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, BallComponent.class);
        createComponent(en, SpriteComponent.class).init(atlas.findRegion("test_region"), 0.036f)
                .setPosition(position.x, position.y);
        createComponent(en, ZIndexComponent.class).zIndex = 2;
        createComponent(en, SizeComponent.class).init(1.16f, 1.16f);
        createComponent(en, PositionComponent.class);
        createComponent(en, BodyComponent.class).init(BodyBuilder.buildCircle(BodyDef.BodyType.DynamicBody, position, 0.58f))
                .setBullet(true)
                .setUserData(en) //fixme
                .setDamping(0, 0)
                .setFriction(0)
                .setFilter(Constants.MASK_BULLET_PLAYER, Constants.CATEGORY_BULLET_PLAYER);

        createComponent(en, LinearImpulseComponent.class).init(direction.x * impulse, direction.y * impulse, 0, 0);
    }
}
