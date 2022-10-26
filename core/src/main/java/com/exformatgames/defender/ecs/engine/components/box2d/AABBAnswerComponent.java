package com.exformatgames.defender.ecs.engine.components.box2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

public class AABBAnswerComponent implements Component {

    public final Array<Body> bodies = new Array<>();

    public static ComponentMapper<AABBAnswerComponent> mapper = ComponentMapper.getFor(AABBAnswerComponent.class);
}
