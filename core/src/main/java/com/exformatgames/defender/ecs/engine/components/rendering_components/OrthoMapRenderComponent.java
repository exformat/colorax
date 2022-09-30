package com.exformatgames.defender.ecs.engine.components.rendering_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class OrthoMapRenderComponent implements Component {

    public OrthogonalTiledMapRenderer mapRenderer;

    public static ComponentMapper<OrthoMapRenderComponent> mapper = ComponentMapper.getFor(OrthoMapRenderComponent.class);

}
