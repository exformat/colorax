package com.exformatgames.defender.ecs.test;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.input_components.button_event_components.ButtonJustPressedComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.button_event_components.ButtonPressedComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.key_events.KeyPressedComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.tiled_components.TiledMapComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.gesture_components.GesturePanComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.NewPositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.ScaleLoopComponent;
import com.exformatgames.defender.ecs.engine.components.util_components.DeleteEntityComponent;

public class TestEntity extends EntityBuilder {

    @Override
    protected void create() {

        TiledMap tiledMap = new TmxMapLoader().load("test_tilemap.tmx");

        createComponent(TestComponent.class);
        createComponent(NewPositionComponent.class).init(0, 0);
        createComponent(PositionComponent.class).init(0, 0);
        createComponent(CullingComponent.class).visibleRadius = 1;
        createComponent(SpriteComponent.class).init(atlas.findRegion("testRegion"), 1);
        createComponent(ZIndexComponent.class).zIndex = 1;
        //createComponent(LinearVelocityComponent.class).init(MathUtils.random(-10f, 10f), MathUtils.random(-10f, 10f));
        createComponent(ScaleLoopComponent.class).init(0.3f, 1.2f, 0.3f, 1.2f, 2, -2);
        createComponent(CameraComponent.class).camera = camera;
        createComponent(TiledMapComponent.class).tiledMap = tiledMap;
        createComponent(OrthoMapRenderComponent.class).mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        createComponent(GesturePanComponent.class);
        createComponent(KeyPressedComponent.class);
        //createComponent(ButtonPressedComponent.class);
        createComponent(ButtonJustPressedComponent.class);
    }
}
