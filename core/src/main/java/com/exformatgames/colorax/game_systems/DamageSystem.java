package com.exformatgames.colorax.game_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.colorax.components.weapon_components.DamageComponent;
import com.exformatgames.colorax.components.HealthComponent;

public class DamageSystem extends IteratingSystem {

    public DamageSystem() {
        super(Family.all(HealthComponent.class, DamageComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent healthComponent = HealthComponent.mapper.get(entity);
        DamageComponent damageComponent = DamageComponent.mapper.get(entity);

        healthComponent.health -= damageComponent.damage;

        entity.remove(DamageComponent.class);
    }
}
