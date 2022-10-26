package com.exformatgames.defender.ecs.engine.components.input_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;

public class MouseComponent implements Component {

    public final Vector2 position = new Vector2();

    public static final ComponentMapper<MouseComponent> mapper = ComponentMapper.getFor(MouseComponent.class);
}
