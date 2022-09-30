package com.exformatgames.defender.ecs.engine.components.tiled_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.maps.tiled.TiledMap;
public class TiledMapComponent implements Component {

    public TiledMap tiledMap;

    public static final ComponentMapper<TiledMapComponent> mapper = ComponentMapper.getFor(TiledMapComponent.class);
}
