package com.exformatgames.colorax.entities.weapons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.colorax.components.weapon_components.BulletComponent;
import com.exformatgames.defender.Constants;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.LinearImpulseComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ParticleEffectComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.RotateComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.RotationComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.SizeComponent;
import com.exformatgames.defender.ecs.engine.components.util_components.RemoveEntityComponent;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public class BlasterBulletBuilder extends BulletBuilder{

    @Override
    public void create(Vector2 position, Vector2 direction) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, BulletComponent.class).init(10, null);

        float impulse = 0.055f;

        createComponent(en, SpriteComponent.class).init(atlas.findRegion("bullet_blaster", 1), 0.001f)
                .setPosition(position.x, position.y)
                .offsetRotation = direction.angleDeg();
        createComponent(en, ZIndexComponent.class).zIndex = 2;
        createComponent(en, SizeComponent.class).init(0.25f, 0.16f);
        createComponent(en, PositionComponent.class).init(position.x, position.y);
        createComponent(en, BodyComponent.class).init(BodyBuilder.buildCircle(BodyDef.BodyType.DynamicBody, position, 0.08f))
                .setFixedRotation(true)
                .setBullet(true)
                .setUserData(en) //fixme
                .setDamping(0.2f, 5)
                .setFilter(Constants.MASK_BULLET_ENEMY, Constants.CATEGORY_BULLET_ENEMY);

        createComponent(en, LinearImpulseComponent.class).init(direction.x * impulse, direction.y * impulse, 0, 0);
        createComponent(en, RemoveEntityComponent.class).timer = 5;

        createComponent(en, ParticleEffectComponent.class).init("bullet_track", position.x, position.y, true);

        //createComponent(en, SoundComponent.class).init(assetManager.get("audio/sound/sound_turret_shot.ogg", Sound.class), 0.2f, true, 0);
        //createComponent(en, PointSoundComponent.class).init(position, 5);

    }

    @Override
    public void create() {

    }
}
