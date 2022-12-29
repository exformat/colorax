package com.exformatgames.defender.ecs.test;

import com.badlogic.gdx.math.MathUtils;
import com.exformatgames.defender.ecs.engine.EntityBuilder;

public class TestMTEntity extends EntityBuilder {

    @Override
    public void create() {
        createComponent(TestMTComponent.class).number = MathUtils.random(0, 100);
    }
}
