package com.exformatgames.colorax.game_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.colorax.components.BulletComponent;
import com.exformatgames.colorax.entities.ExplosionEntityBuilder;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.RemoveBodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.contact_components.BeginContactComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.util_components.DeleteEntityComponent;

public class BulletContactSystem extends IteratingSystem {

    public BulletContactSystem() {
        super(Family.all(BulletComponent.class, BeginContactComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DeleteEntityComponent deleteEntityComponent = DeleteEntityComponent.mapper.get(entity);
        deleteEntityComponent.timer = 0.35f;

        EntityBuilder.createComponent(entity, RemoveBodyComponent.class);
        entity.remove(SpriteComponent.class);

        PositionComponent positionComponent = PositionComponent.mapper.get(entity);

        ExplosionEntityBuilder.create(positionComponent.x, positionComponent.y);
    }
}
