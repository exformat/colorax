package com.exformatgames.colorax.components.weapon_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class ExplosionComponent implements Component {

    public float damage = 0;

    public float radius = 1;
    public  float impulse = 10;

    public ExplosionComponent init(float damage, float impulse, float radius) {
        this.damage = damage;
        this.impulse = impulse;
        this.radius = radius;

        return  this;
    }

    public static ComponentMapper<ExplosionComponent> mapper = ComponentMapper.getFor(ExplosionComponent.class);
}
