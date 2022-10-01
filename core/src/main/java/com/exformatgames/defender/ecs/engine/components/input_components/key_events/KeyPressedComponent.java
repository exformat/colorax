package com.exformatgames.defender.ecs.engine.components.input_components.key_events;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Array;

public class KeyPressedComponent implements Component {

    public Array<Integer> keys = new Array<>();

    public static final ComponentMapper<KeyPressedComponent> mapper = ComponentMapper.getFor(KeyPressedComponent.class);

}
