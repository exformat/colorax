package com.exformatgames.colorax.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.exformatgames.colorax.components.weapon_components.ExplosionComponent;
import com.exformatgames.colorax.entities.weapons.ActionBulletContactBuilder;
import com.exformatgames.defender.ecs.engine.components.audio_components.PointSoundComponent;
import com.exformatgames.defender.ecs.engine.components.audio_components.SoundComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.AABBQueryComponent;
import com.exformatgames.defender.ecs.engine.components.light_components.LightComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.AnimationComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.NewPositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.util_components.RemoveEntityComponent;

public class ExplosionTankBulletEntityBuilder extends ActionBulletContactBuilder {

    @Override
    public void create() {
    }

    @Override
    public void create(Vector2 position, Vector2 direction) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        float x = position.x;
        float y = position.y;

        createComponent(en, ExplosionComponent.class).init(5, 2, 2);

        int index = MathUtils.random(1) > 0.5f ? 2 : 3;

        Array<TextureAtlas.AtlasRegion> regions = (atlas.findRegions("exp_" + index));
        createComponent(en, AnimationComponent.class).init("", 1f / 30f, regions, Animation.PlayMode.NORMAL, 0.02f);
        createComponent(en, SpriteComponent.class).init(regions.first(), 0.02f, -0.64f, -0.64f)
                .setPosition(x, y)
                .rotation(direction.angleDeg() + 90);
        createComponent(en, ZIndexComponent.class).zIndex = 2;

        createComponent(en, LightComponent.class).init(LightComponent.LightType.POINT, new Vector2(x, y), Color.GOLD, 0, 100, 1, 0)
                .light.setSoft(true);

        createComponent(en, NewPositionComponent.class).init(x, y);
        createComponent(en, PositionComponent.class).init(x, y);
        createComponent(en, RemoveEntityComponent.class).timer = 1;

        createComponent(en, SoundComponent.class).init(assetManager.get("audio/sound/sound_explosion_bullet.ogg", Sound.class), 1, true, 0);
        createComponent(en, PointSoundComponent.class).init(x, y, 20);
        createComponent(en, AABBQueryComponent.class).init(x - 1, y - 1, x + 1, y + 1);

    }

    public static void create(float x, float y){
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, ExplosionComponent.class).init(5, 2, 2);

        Array<TextureAtlas.AtlasRegion> regions = (atlas.findRegions("exp_3"));
        createComponent(en, AnimationComponent.class).init("", 1f / 30f, regions, Animation.PlayMode.NORMAL, 0.02f);
        createComponent(en, SpriteComponent.class).init(regions.first(), 0.02f, -0.64f, -0.64f)
                .setPosition(x, y);
        createComponent(en, ZIndexComponent.class).zIndex = 2;

        createComponent(en, LightComponent.class).init(LightComponent.LightType.POINT, new Vector2(x, y), Color.GOLD, 0, 100, 1, 0)
                .light.setSoft(true);

        createComponent(en, NewPositionComponent.class).init(x, y);
        createComponent(en, PositionComponent.class).init(x, y);
        createComponent(en, RemoveEntityComponent.class).timer = 1;

        createComponent(en, SoundComponent.class).init(assetManager.get("audio/sound/sound_explosion_bullet.ogg", Sound.class), 1, true, 0);
        createComponent(en, PointSoundComponent.class).init(x, y, 20);
        createComponent(en, AABBQueryComponent.class).init(x - 1, y - 1, x + 1, y + 1);
    }
}
