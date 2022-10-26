package com.exformatgames.colorax.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.exformatgames.defender.ecs.engine.components.box2d.AABBAnswerComponent;

public class ExplosionComponent implements Component {

    public float damage = 0;
    public  float impulse = 10;

    public ExplosionComponent init(float damage, float impulse) {
        this.damage = damage;
        this.impulse = impulse;

        return  this;
    }

    public static ComponentMapper<ExplosionComponent> mapper = ComponentMapper.getFor(ExplosionComponent.class);

}
