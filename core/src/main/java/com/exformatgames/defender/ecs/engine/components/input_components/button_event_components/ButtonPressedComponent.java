package com.exformatgames.defender.ecs.engine.components.input_components.button_event_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ButtonPressedComponent implements Component {

    public Array<Integer> buttons = new Array<>();
    public Vector2 mousePosition = new Vector2();

    public static final ComponentMapper<ButtonPressedComponent> mapper = ComponentMapper.getFor(ButtonPressedComponent.class);

}
