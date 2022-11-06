package com.exformatgames.colorax.entities.enemies;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Array;
import com.exformatgames.colorax.components.HealthComponent;
import com.exformatgames.colorax.components.enemy_components.EnemyComponent;
import com.exformatgames.colorax.components.enemy_components.TankEnemyComponent;
import com.exformatgames.colorax.components.weapon_components.WeaponComponent;
import com.exformatgames.colorax.entities.weapons.BlasterBulletBuilder;
import com.exformatgames.defender.Constants;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.AnimationComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.NewPositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.SizeComponent;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public class TankEnemyBuilder extends EnemyEntityBuilder{

    @Override
    public void create(float x, float y) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, SizeComponent.class).init(1.92f, 1.92f);
        createComponent(en, BodyComponent.class).init(BodyBuilder.buildBox(BodyDef.BodyType.DynamicBody, new Vector2(x, y), 1.92f, 1.38f))
                .setDamping(5, 5)
                .setUserData(en)
                .setFilter(Constants.MASK_ENEMY, Constants.CATEGORY_ENEMY);

        Array<TextureAtlas.AtlasRegion> regions = (atlas.findRegions("track"));
        createComponent(en, AnimationComponent.class).init("", 1f / 8f, regions, Animation.PlayMode.LOOP, 0.03f);
        createComponent(en, SpriteComponent.class).init(regions.first(), 0.03f, 0f, 0f);

        Array<TextureAtlas.AtlasRegion> regions2 = (atlas.findRegions("tank_1_enemy"));
        createComponent(en, AnimationComponent.class).init("", 1f / 8f, regions2, Animation.PlayMode.LOOP, 0.03f);
        createComponent(en, SpriteComponent.class).init(regions.first(), 0.03f, 0f, 0f);
        createComponent(en, SpriteComponent.class).init(atlas.findRegion("tank_1_enemy_canon"), 0.03f, 0f, 0f);

        createComponent(en, ZIndexComponent.class).zIndex = 2;

        createComponent(en, NewPositionComponent.class).init(x, y);
        createComponent(en, PositionComponent.class).init(x, y);

        createComponent(en, TankEnemyComponent.class).init(5f, 0.01f, 0, 1);
        createComponent(en, HealthComponent.class).health = 1200;

        createComponent(en, WeaponComponent.class).init(1, 10, 1).bulletBuilder = new BlasterBulletBuilder();

    }

    @Override
    public void create() {

    }
}
