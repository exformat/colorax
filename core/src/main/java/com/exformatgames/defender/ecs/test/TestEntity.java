package com.exformatgames.defender.ecs.test;

import com.badlogic.gdx.math.MathUtils;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.rendering_components.CullingComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.LinearVelocityComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.ScaleLoopComponent;

public class TestEntity extends EntityBuilder {

    @Override
    protected void create() {
        createComponent(TestComponent.class);
        createComponent(SpriteComponent.class).init(atlas.findRegion("testRegion"), 1);
        createComponent(ZIndexComponent.class).zIndex = 1;
        createComponent(PositionComponent.class).init(0, 0);
        createComponent(LinearVelocityComponent.class).init(MathUtils.random(-10f, 10f), MathUtils.random(-10f, 10f));
        createComponent(CullingComponent.class).visibleRadius = 1;
        createComponent(ScaleLoopComponent.class).init(0.3f, 1.2f, 0.9f, 1.1f, 2, 8);


    }
}
