package com.exformatgames.colorax.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.exformatgames.colorax.components.ExplosionComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.AnimationComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.NewPositionComponent;
import com.exformatgames.defender.ecs.engine.components.util_components.DeleteEntityComponent;

public class ExplosionEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
    }

    public void create(float x, float y){

        createComponent(ExplosionComponent.class);

        Array<TextureAtlas.AtlasRegion> regions = (atlas.findRegions("exp_3"));
        createComponent(AnimationComponent.class).init(1f / 30f, regions, Animation.PlayMode.NORMAL, 0.02f);
        createComponent(SpriteComponent.class).init(regions.first(), 0.01f);
        createComponent(ZIndexComponent.class).zIndex = 2;

        createComponent(NewPositionComponent.class).init(x - 0.64f, y - 0.64f);
        //createComponent(DeleteEntityComponent.class).timer = 1;
    }
}
