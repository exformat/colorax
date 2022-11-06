package com.exformatgames.defender.ecs.engine.components.util_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class RemoveEntityComponent implements Component {

    public float timer = -1;

    public static ComponentMapper<RemoveEntityComponent> mapper = ComponentMapper.getFor(RemoveEntityComponent.class);

}
