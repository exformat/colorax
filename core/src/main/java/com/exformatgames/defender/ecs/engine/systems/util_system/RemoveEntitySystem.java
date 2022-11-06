package com.exformatgames.defender.ecs.engine.systems.util_system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.light_components.LightComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ParticleEffectComponent;
import com.exformatgames.defender.ecs.engine.components.util_components.RemoveEntityComponent;
import com.exformatgames.defender.ecs.utils.Particles;

public class RemoveEntitySystem extends IteratingSystem {

    private final World box2dWorld;

    public RemoveEntitySystem(World box2dWorld) {
        super(Family.one(RemoveEntityComponent.class).get());
        this.box2dWorld = box2dWorld;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RemoveEntityComponent removeEntityComponent = RemoveEntityComponent.mapper.get(entity);
        if ((removeEntityComponent.timer -= deltaTime) < 0){

            BodyComponent bodyComponent = BodyComponent.mapper.get(entity);
            if (bodyComponent != null){
                bodyComponent.body.setActive(false);
                box2dWorld.destroyBody(bodyComponent.body);
            }

            LightComponent lightComponent = LightComponent.mapper.get(entity);
            if (lightComponent != null) {
                lightComponent.light.remove();
            }

            ParticleEffectComponent particleEmitterComponent = ParticleEffectComponent.mapper.get(entity);
            if (particleEmitterComponent != null){
                Particles.GET(particleEmitterComponent.name).free(particleEmitterComponent.pooledEffect);

                particleEmitterComponent.isDraw = false;
            }

            entity.removeAll();

            getEngine().removeEntity(entity);
        }
    }
}
