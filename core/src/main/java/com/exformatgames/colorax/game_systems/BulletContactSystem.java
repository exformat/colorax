package com.exformatgames.colorax.game_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.exformatgames.colorax.components.weapon_components.BulletComponent;
import com.exformatgames.colorax.components.weapon_components.DamageComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.RemoveBodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.contact_components.BeginContactComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.util_components.RemoveEntityComponent;

public class BulletContactSystem extends IteratingSystem {

    public BulletContactSystem() {
        super(Family.all(BulletComponent.class, BeginContactComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BulletComponent bulletComponent = BulletComponent.mapper.get(entity);

        RemoveEntityComponent removeEntityComponent = RemoveEntityComponent.mapper.get(entity);
        removeEntityComponent.timer = 0;

        BeginContactComponent beginContactComponent = BeginContactComponent.mapper.get(entity);
        if (beginContactComponent != null) {
            EntityBuilder.createComponent(beginContactComponent.contactEntity, DamageComponent.class).damage = bulletComponent.damage;
        }

        EntityBuilder.createComponent(entity, RemoveBodyComponent.class);
        entity.remove(SpriteComponent.class);

        PositionComponent positionComponent = PositionComponent.mapper.get(entity);

        if (bulletComponent.actionContactBuilder != null){
            bulletComponent.actionContactBuilder.create(new Vector2(positionComponent.x, positionComponent.y), bulletComponent.direction);
        }
    }
}
