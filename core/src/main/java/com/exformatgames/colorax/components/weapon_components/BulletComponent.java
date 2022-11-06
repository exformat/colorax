package com.exformatgames.colorax.components.weapon_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.exformatgames.colorax.entities.weapons.ActionBulletContactBuilder;
import com.exformatgames.defender.ecs.engine.EntityBuilder;

public class BulletComponent implements Component {

    public float damage = 10;
    public Vector2 direction = new Vector2();

    public ActionBulletContactBuilder actionContactBuilder;

    public BulletComponent init(float damage, ActionBulletContactBuilder actionContactBuilder) {
        this.damage = damage;
        this.actionContactBuilder = actionContactBuilder;

        return this;
    }

    public BulletComponent init(float damage, Vector2 direction, ActionBulletContactBuilder actionContactBuilder) {
        this.damage = damage;
        this.direction.set(direction);
        this.actionContactBuilder = actionContactBuilder;

        return this;
    }

    public static ComponentMapper<BulletComponent> mapper = ComponentMapper.getFor(BulletComponent.class);
}
