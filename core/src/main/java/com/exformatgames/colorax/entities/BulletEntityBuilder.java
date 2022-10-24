package com.exformatgames.colorax.entities;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.colorax.components.BulletComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.LinearImpulseComponent;
import com.exformatgames.defender.ecs.engine.components.light_components.LightComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ParticleEffectComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ParticleEmitterComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.SizeComponent;
import com.exformatgames.defender.ecs.engine.components.util_components.DeleteEntityComponent;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public class BulletEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
    }

    public void init(Vector2 position, Vector2 direction, float impulse){

        createComponent(BulletComponent.class);

        createComponent(SpriteComponent.class).init(atlas.findRegion("test_region"), 0.005f);
        createComponent(ZIndexComponent.class).zIndex = 2;
        createComponent(SizeComponent.class).init(0.16f, 0.16f);
        createComponent(PositionComponent.class);
        createComponent(BodyComponent.class).init(BodyBuilder.buildCircle(BodyDef.BodyType.DynamicBody, position, 0.08f))
                .setFixedRotation(true)
                .setUserData(entity) //fixme
                .setDamping(0.2f, 5);

        createComponent(LinearImpulseComponent.class).init(direction.x * impulse, direction.y * impulse, 0, 0);
        createComponent(DeleteEntityComponent.class).timer = 3;

        createComponent(LightComponent.class).init(LightComponent.LightType.POINT, position, null, 0, 100, 1.5f, 0).light.attachToBody(BodyComponent.mapper.get(entity).body);

        createComponent(ParticleEffectComponent.class).init("bullet_track", position.x, position.y, true);

    }
}
