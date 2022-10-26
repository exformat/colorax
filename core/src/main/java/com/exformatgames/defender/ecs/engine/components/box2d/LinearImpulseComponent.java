package com.exformatgames.defender.ecs.engine.components.box2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class LinearImpulseComponent implements Component {

    public float impulseX = 0;
    public float impulseY = 0;

    public float pointX = 0;
    public float pointY = 0;

    public void init(float impulseX, float impulseY, float pointX, float pointY) {
        this.impulseX = impulseX;
        this.impulseY = impulseY;
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public static ComponentMapper<LinearImpulseComponent> mapper = ComponentMapper.getFor(LinearImpulseComponent.class);
}
