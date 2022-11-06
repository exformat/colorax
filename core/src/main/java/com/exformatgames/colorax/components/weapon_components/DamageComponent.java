package com.exformatgames.colorax.components.weapon_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class DamageComponent implements Component {

    public Entity target;
    public Entity dealer;

    public float damage;

    public static ComponentMapper<DamageComponent> mapper = ComponentMapper.getFor(DamageComponent.class);
}
