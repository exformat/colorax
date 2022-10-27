package com.exformatgames.colorax.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Array;
import com.exformatgames.colorax.components.EnemyComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.AnimationComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.NewPositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.SizeComponent;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public class EnemyEntityBuilder extends EntityBuilder {

    @Override
    public void create() {

    }

    public static void create(float x, float y) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, SizeComponent.class).init(0.41f, 0.59f);
        createComponent(en, BodyComponent.class).init(BodyBuilder.buildCircle(BodyDef.BodyType.DynamicBody, new Vector2(x, y), 0.205f))
                .setDamping(5, 5)
                .setUserData(en)
                .setFixedRotation(true);

        Array<TextureAtlas.AtlasRegion> regions = (atlas.findRegions("enemy_1_walk"));
        createComponent(en, AnimationComponent.class).init(1f / 8f, regions, Animation.PlayMode.LOOP, 0.01f);
        createComponent(en, SpriteComponent.class).init(regions.first(), 0.01f, 0f, 0f);
        createComponent(en, ZIndexComponent.class).zIndex = 2;

        createComponent(en, NewPositionComponent.class).init(x, y);
        createComponent(en, PositionComponent.class).init(x, y);

        createComponent(en, EnemyComponent.class);
    }
}
