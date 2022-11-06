package com.exformatgames.colorax.game_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.colorax.components.HealthComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.util_components.RemoveEntityComponent;

public class DeathSystem extends IteratingSystem {

    public DeathSystem() {
        super(Family.all(HealthComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent healthComponent = HealthComponent.mapper.get(entity);
        if (healthComponent.health < 0){
            EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
        }
    }
}
