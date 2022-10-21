package com.exformatgames.colorax.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;

public class TankComponent implements Component {

    public final Vector2 turretPosition = new Vector2();
    public final Vector2 turretTarget = new Vector2(500, 500);
    public final Vector2 bodyTarget = new Vector2();


    public static ComponentMapper<TankComponent> mapper = ComponentMapper.getFor(TankComponent.class);

}
