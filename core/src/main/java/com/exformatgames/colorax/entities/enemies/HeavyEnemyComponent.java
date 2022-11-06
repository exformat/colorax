package com.exformatgames.colorax.entities.enemies;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Array;
import com.exformatgames.colorax.components.HealthComponent;
import com.exformatgames.colorax.components.enemy_components.EnemyComponent;
import com.exformatgames.colorax.components.weapon_components.WeaponComponent;
import com.exformatgames.colorax.entities.weapons.BlasterBulletBuilder;
import com.exformatgames.colorax.entities.weapons.DarkmatterBulletBuilder;
import com.exformatgames.defender.Constants;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public class HeavyEnemyComponent  extends EnemyEntityBuilder {

    @Override
    public void create() {

    }

    public void create(float x, float y) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, SizeComponent.class).init(0.59f, 0.41f);
        createComponent(en, BodyComponent.class).init(BodyBuilder.buildCircle(BodyDef.BodyType.DynamicBody, new Vector2(x, y), 0.205f))
                .setDamping(5, 5)
                .setUserData(en)
                .setFixedRotation(true)
                .setFilter(Constants.MASK_ENEMY, Constants.CATEGORY_ENEMY);

        Animation<TextureAtlas.AtlasRegion> walkAnimation = createAnimation("enemy_3_walk", 1f / 8);
        Animation<TextureAtlas.AtlasRegion> idleAnimation = createAnimation("enemy_3_idle", 1f / 8);

        createComponent(en, AnimationsComponent.class).init(new String[]{"WALK", "IDLE"}, new Animation[]{walkAnimation, idleAnimation});
        createComponent(en, AnimationComponent.class).init("WALK", 1f / 8f, walkAnimation, Animation.PlayMode.LOOP, 0.01f);


        createComponent(en, SpriteComponent.class).init(walkAnimation.getKeyFrame(0), 0.01f, 0f, 0f);
        createComponent(en, ZIndexComponent.class).zIndex = 2;

        createComponent(en, NewPositionComponent.class).init(x, y);
        createComponent(en, PositionComponent.class).init(x, y);

        createComponent(en, EnemyComponent.class).init(0.15f, 0.15f, -0.295f);
        createComponent(en, HealthComponent.class).health = 250;

        createComponent(en, WeaponComponent.class).init(1, 6, 1).bulletBuilder = new DarkmatterBulletBuilder();
    }
}

