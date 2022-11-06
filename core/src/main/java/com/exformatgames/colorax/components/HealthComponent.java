package com.exformatgames.colorax.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class HealthComponent implements Component {

    public float health = 100;

    public static ComponentMapper<HealthComponent> mapper = ComponentMapper.getFor(HealthComponent.class);
}
