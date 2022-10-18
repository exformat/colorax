package com.exformatgames.colorax.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.exformatgames.colorax.components.TankComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.input_components.MouseComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.button_event_components.ButtonJustPressedComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.gesture_components.GesturePanComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.key_events.KeyPressedComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.CullingComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.NewPositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.SizeComponent;

public class TankEntityBuilder extends EntityBuilder {
    @Override
    public void create() {

        createComponent(TankComponent.class).turretPosition.set(0, (192f / 2 - 169f / 2 - 169f / 4));

        createComponent(NewPositionComponent.class).init(512, 512);
        createComponent(PositionComponent.class).init(512, 512);
        createComponent(CullingComponent.class).visibleRadius = 1;

        createComponent(SpriteComponent.class).init(atlas.findRegion("body"), 1);
        createComponent(SpriteComponent.class).
                init(atlas.findRegion("turret"), 1)
                .setOrigin(93f / 2f, 169f / 3);

        createComponent(ZIndexComponent.class).zIndex = 1;

        createComponent(MouseComponent.class);
        createComponent(KeyPressedComponent.class);
        createComponent(ButtonJustPressedComponent.class);

        createComponent(SizeComponent.class).init(147, 192);
    }
}
