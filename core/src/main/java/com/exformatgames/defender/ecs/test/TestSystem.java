package com.exformatgames.defender.ecs.test;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.defender.ecs.engine.components.rendering_components.CullingComponent;
import com.exformatgames.defender.ecs.engine.components.touch_components.GesturePanComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.TranslateComponent;

public class TestSystem extends IteratingSystem {

    public TestSystem() {
        super(Family.one(TestComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CullingComponent cullingComponent = CullingComponent.mapper.get(entity);
        GesturePanComponent gesturePanComponent = GesturePanComponent.mapper.get(entity);
        TranslateComponent translateComponent = getEngine().createComponent(TranslateComponent.class);

        if(gesturePanComponent != null){
            translateComponent.x = gesturePanComponent.delta.x;
            translateComponent.y = gesturePanComponent.delta.y;
            entity.add(translateComponent);
        }

        if (! cullingComponent.inViewport){
            entity.add(getEngine().createComponent(PositionComponent.class).init(0, 0));
        }
    }
}
