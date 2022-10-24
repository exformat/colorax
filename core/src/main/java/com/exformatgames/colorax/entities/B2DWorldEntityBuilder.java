package com.exformatgames.colorax.entities;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.WorldComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.LightRenderComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ParticleEffectComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;

public class B2DWorldEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(WorldComponent.class).init(world, 4, 4);

        LightRenderComponent.RAY_HANDLER = new RayHandler(world);
        LightRenderComponent.RAY_HANDLER.setAmbientLight(0.7f, 0.7f, 0.7f, 0.9f);

        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);
        LightRenderComponent.RAY_HANDLER.setBlurNum(3);
        LightRenderComponent.RAY_HANDLER.setCulling(true);

        createComponent(LightRenderComponent.class);
        createComponent(ZIndexComponent.class).zIndex = 2;
    }
}
