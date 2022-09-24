package com.exformatgames.defender.ecs.test;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.exformatgames.defender.ecs.engine.components.rendering_components.CullingComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;

public class TestSystem extends IteratingSystem {

    public TestSystem() {
        super(Family.one(TestComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CullingComponent cullingComponent = CullingComponent.mapper.get(entity);

        if (! cullingComponent.inViewport){
            entity.add(getEngine().createComponent(PositionComponent.class).init(0, 0));

        }
    }
}
