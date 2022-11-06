package com.exformatgames.colorax.components.enemy_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class TankEnemyComponent implements Component {

    public float force = 10;
    public float angularForce = 1;
    public Vector2 direction = new Vector2(0, 1);
    public Entity target;
    public Vector2 weaponPosition = new Vector2();

    public TankEnemyComponent init(float force, float angularForce, Vector2 weaponPosition) {
        this.force = force;
        this.angularForce = angularForce;
        this.weaponPosition.set(weaponPosition);

        target = null;
        direction.set(0, 1);

        return this;
    }

    public TankEnemyComponent init(float force, float angularForce, float weaponPositionX, float weaponPositionY) {
        this.force = force;
        this.angularForce = angularForce;
        this.weaponPosition.set(weaponPositionX, weaponPositionY);

        target = null;
        direction.set(0, 1);

        return this;
    }

    public static ComponentMapper<TankEnemyComponent> mapper = ComponentMapper.getFor(TankEnemyComponent.class);
}
