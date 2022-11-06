package com.exformatgames.colorax.game_systems.enemy_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.colorax.components.enemy_components.EnemySpawnComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.util_components.RemoveEntityComponent;

public class EnemySpawnSystem extends IteratingSystem {

    public EnemySpawnSystem() {
        super(Family.all(EnemySpawnComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        EnemySpawnComponent spawnComponent = EnemySpawnComponent.mapper.get(entity);

        if((spawnComponent.timer += deltaTime) > spawnComponent.interval){
            spawnComponent.timer = 0;
            spawnComponent.count--;
            spawnComponent.enemyBuilder.create(spawnComponent.x, spawnComponent.y);
        }
        if (spawnComponent.count == 0){
            EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
        }
    }
}
