package com.exformatgames.defender.ecs.test;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.WorldComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.button_event_components.ButtonJustPressedComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.key_events.KeyPressedComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.tiled_components.TiledMapComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.gesture_components.GesturePanComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public class TestEntity extends EntityBuilder {

    @Override
    public void create() {

        TiledMap tiledMap = new TmxMapLoader().load("test_tilemap.tmx");

        createComponent(TestComponent.class);
        createComponent(NewPositionComponent.class).init(0, 0);
        createComponent(PositionComponent.class).init(0, 0);
        createComponent(CullingComponent.class).visibleRadius = 1;
        createComponent(SpriteComponent.class).init(atlas.findRegion("testRegion"), 1);
            createComponent(SpriteComponent.class).init(atlas.findRegion("testRegion"), 1, 10, 10);
            createComponent(SpriteComponent.class).init(atlas.findRegion("testRegion"), 0.5f, 50, 50);
            createComponent(SpriteComponent.class).init(atlas.findRegion("testRegion"), 0.3f, 100, 100);


        createComponent(ZIndexComponent.class).zIndex = 1;
        //createComponent(LinearVelocityComponent.class).init(MathUtils.random(-10f, 10f), MathUtils.random(-10f, 10f));
        createComponent(ScaleLoopComponent.class).init(0.3f, 1.2f, 0.3f, 1.2f, 8, -2);
        createComponent(CameraComponent.class).camera = camera;
        createComponent(TiledMapComponent.class).tiledMap = tiledMap;
        createComponent(OrthoMapRenderComponent.class).mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        createComponent(WorldComponent.class).init(world, 4, 4);

        createComponent(SizeComponent.class).init(atlas.findRegion("testRegion").getRegionWidth(), atlas.findRegion("testRegion").getRegionHeight());
        createComponent(BodyComponent.class).init(BodyBuilder.buildCircle(BodyDef.BodyType.DynamicBody, new Vector2(0, 0), 10))
                .setFixedRotation(true);

        createComponent(GesturePanComponent.class);
        createComponent(KeyPressedComponent.class);
        //createComponent(ButtonPressedComponent.class);
        createComponent(ButtonJustPressedComponent.class);
    }
}
