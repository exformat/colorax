package com.exformatgames.colorax.components.weapon_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.exformatgames.colorax.entities.weapons.BulletBuilder;

public class WeaponComponent implements Component {

    public float DPS = 10;
    public float radius = 5;
    public float damage = 5;

    public float impulse = 10;

    public float timer = 0;
    public boolean active = false;

    public Vector2 dir = new Vector2();
    public Vector2 position = new Vector2();

    public BulletBuilder bulletBuilder;

    public WeaponComponent init(float DPS, float radius, float damage) {
        this.DPS = DPS;
        this.radius = radius;
        this.damage = damage;

        return this;
    }

    public static ComponentMapper<WeaponComponent> mapper = ComponentMapper.getFor(WeaponComponent.class);
}
