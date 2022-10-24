package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.defender.ecs.engine.components.box2d.contact_components.BeginContactComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.contact_components.EndContactComponent;

public class CollisionClearSystem extends IteratingSystem {

    public CollisionClearSystem() {
        super(Family.one(BeginContactComponent.class, EndContactComponent.class).get());
    }



    @Override
    protected void processEntity(Entity entity, float dt) {
        BeginContactComponent beginContactComponent = BeginContactComponent.mapper.get(entity);
        EndContactComponent endContactComponent = EndContactComponent.mapper.get(entity);

        if (beginContactComponent != null) {
            entity.remove(BeginContactComponent.class);
        }
        if (endContactComponent != null) {
            entity.remove(EndContactComponent.class);
        }
    }
}