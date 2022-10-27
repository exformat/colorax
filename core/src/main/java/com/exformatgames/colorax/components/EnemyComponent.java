package com.exformatgames.colorax.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class EnemyComponent implements Component {

    public float dps = 0;
    public float force = 0.2f;
    public Entity target;

    public static ComponentMapper<EnemyComponent> mapper = ComponentMapper.getFor(EnemyComponent.class);

}
