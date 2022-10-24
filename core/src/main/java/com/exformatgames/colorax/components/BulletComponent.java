package com.exformatgames.colorax.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class BulletComponent implements Component {

    public float damage = 100;
    public float damageRadius = 0.5f;

    public static ComponentMapper<BulletComponent> mapper = ComponentMapper.getFor(BulletComponent.class);
}
