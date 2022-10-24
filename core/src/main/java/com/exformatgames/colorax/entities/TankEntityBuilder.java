package com.exformatgames.colorax.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.colorax.components.PLayerComponent;
import com.exformatgames.colorax.components.TankComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.RayComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.MouseComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.button_event_components.ButtonJustPressedComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.key_events.KeyPressedComponent;
import com.exformatgames.defender.ecs.engine.components.light_components.LightComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.CullingComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.NewPositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.SizeComponent;
import com.exformatgames.defender.ecs.utils.BodyBuilder;

public class TankEntityBuilder extends EntityBuilder {
    @Override
    public void create() {

        createComponent(TankComponent.class).turretPosition.set(0, (1.92f / 2 - 1.69f / 2 - 1.69f / 4));
        createComponent(PLayerComponent.class);

        createComponent(RayComponent.class);

        createComponent(NewPositionComponent.class).init(5.12f, 5.12f);
        createComponent(PositionComponent.class).init(5.12f, 5.12f);
        createComponent(CullingComponent.class).visibleRadius = 100;

        createComponent(BodyComponent.class).init(BodyBuilder.buildBox(BodyDef.BodyType.DynamicBody, new Vector2(5.12f, 5.12f), 1.47f, 1.93f))
                .setDamping(8, 10)
                .body.setUserData(entity);


        createComponent(SpriteComponent.class).init(atlas.findRegion("body"), 0.01f);
        createComponent(SpriteComponent.class).
                init(atlas.findRegion("turret"), 0.01f)
                .setOrigin(0.93f / 2f, 1.69f / 3);

        createComponent(ZIndexComponent.class).zIndex = 1;

        createComponent(MouseComponent.class);
        createComponent(KeyPressedComponent.class);
        createComponent(ButtonJustPressedComponent.class);

        createComponent(SizeComponent.class).init(1.47f, 1.92f);

        //createComponent(LightComponent.class).init(LightComponent.LightType.CONE, new Vector2(5, 5), Color.BLUE, 10, 100, 2, 100).light.attachToBody(BodyComponent.mapper.get(entity).body);
    }
}
