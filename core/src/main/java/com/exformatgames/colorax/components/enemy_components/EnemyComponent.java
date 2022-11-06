package com.exformatgames.colorax.components.enemy_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class EnemyComponent implements Component {

    public float force = 0.2f;
    public Vector2 weaponPosition = new Vector2();

    public EnemyComponent init(float force, Vector2 weaponPosition) {
        this.force = force;
        this.weaponPosition = weaponPosition;

        return this;
    }

    public EnemyComponent init(float force, float weaponPositionX, float weaponPositionY) {
        this.force = force;
        weaponPosition.set(weaponPositionX, weaponPositionY);

        return this;
    }

    public Entity target;

    public static ComponentMapper<EnemyComponent> mapper = ComponentMapper.getFor(EnemyComponent.class);

}
