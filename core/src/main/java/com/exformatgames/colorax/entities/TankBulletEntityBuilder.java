package com.exformatgames.colorax.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.colorax.components.weapon_components.BulletComponent;
import com.exformatgames.defender.Constants;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.audio_components.SoundComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.LinearImpulseComponent;
import com.exformatgames.defender.ecs.engine.components.light_components.LightComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ParticleEffectComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.SizeComponent;
import com.exformatgames.defender.ecs.engine.components.util_components.RemoveEntityComponent;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public class TankBulletEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
    }

    public static void create(Vector2 position, Vector2 direction, float impulse) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, BulletComponent.class).init(10, direction, new ExplosionTankBulletEntityBuilder());
        createComponent(en, SpriteComponent.class).init(atlas.findRegion("test_region"), 0.005f)
                .setPosition(position.x, position.y);
        createComponent(en, ZIndexComponent.class).zIndex = 2;
        createComponent(en, SizeComponent.class).init(0.16f, 0.16f);
        createComponent(en, PositionComponent.class);
        createComponent(en, BodyComponent.class).init(BodyBuilder.buildCircle(BodyDef.BodyType.DynamicBody, position, 0.08f))
                .setFixedRotation(true)
                .setBullet(true)
                .setUserData(en) //fixme
                .setDamping(0.2f, 5)
                .setFilter(Constants.MASK_BULLET_PLAYER, Constants.CATEGORY_BULLET_PLAYER);

        createComponent(en, LinearImpulseComponent.class).init(direction.x * impulse, direction.y * impulse, 0, 0);
        createComponent(en, RemoveEntityComponent.class).timer = 3;

        createComponent(en, LightComponent.class).init(LightComponent.LightType.POINT, position, null, 0, 100, 0.6f, 0).light.attachToBody(BodyComponent.mapper.get(en).body);

        createComponent(en, ParticleEffectComponent.class).init("bullet_track", position.x, position.y, true);

        createComponent(en, SoundComponent.class).init(assetManager.get("audio/sound/sound_turret_shot.ogg", Sound.class), 0.5f, true, 0);
    }
}
