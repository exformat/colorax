package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.LinearImpulseComponent;

public class LinearImpulseSystem extends IteratingSystem {

    public LinearImpulseSystem() {
        super(Family.one(LinearImpulseComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        LinearImpulseComponent impulseComponent = LinearImpulseComponent.mapper.get(entity);
        BodyComponent bodyComponent = BodyComponent.mapper.get(entity);

        bodyComponent.body.applyLinearImpulse(impulseComponent.impulseX, impulseComponent.impulseY, impulseComponent.pointX, impulseComponent.pointY, true);

        entity.remove(LinearImpulseComponent.class);
    }
}
