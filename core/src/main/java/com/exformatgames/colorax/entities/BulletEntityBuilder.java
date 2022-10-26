package com.exformatgames.colorax.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.colorax.components.BulletComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.audio_components.SoundComponent;
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

    public static void create(Vector2 position, Vector2 direction, float impulse) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, BulletComponent.class);

        createComponent(en, SpriteComponent.class).init(atlas.findRegion("test_region"), 0.005f);
        createComponent(en, ZIndexComponent.class).zIndex = 2;
        createComponent(en, SizeComponent.class).init(0.16f, 0.16f);
        createComponent(en, PositionComponent.class);
        createComponent(en, BodyComponent.class).init(BodyBuilder.buildCircle(BodyDef.BodyType.DynamicBody, position, 0.08f))
                .setFixedRotation(true)
                .setUserData(en) //fixme
                .setDamping(0.2f, 5);

        createComponent(en, LinearImpulseComponent.class).init(direction.x * impulse, direction.y * impulse, 0, 0);
        createComponent(en, DeleteEntityComponent.class).timer = 3;

        createComponent(en, LightComponent.class).init(LightComponent.LightType.POINT, position, null, 0, 100, 0.6f, 0).light.attachToBody(BodyComponent.mapper.get(en).body);

        createComponent(en, ParticleEffectComponent.class).init("bullet_track", position.x, position.y, true);

        createComponent(en, SoundComponent.class).init(assetManager.get("audio/sound/sound_turret_shot.ogg", Sound.class), 0.5f, true, 0);
    }
}
