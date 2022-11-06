package com.exformatgames.colorax.game_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.exformatgames.colorax.components.PLayerComponent;
import com.exformatgames.colorax.components.TankComponent;
import com.exformatgames.colorax.entities.TankBulletEntityBuilder;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.AngularImpulseComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.ForceComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.button_event_components.ButtonJustPressedComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.key_events.KeyPressedComponent;

public class PlayerControlSystem extends IteratingSystem {

    public PlayerControlSystem() {
        super(Family.all(PLayerComponent.class, ButtonJustPressedComponent.class, KeyPressedComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        KeyPressedComponent keyPressedComponent = KeyPressedComponent.mapper.get(entity);
        TankComponent tankComponent = TankComponent.mapper.get(entity);
        ButtonJustPressedComponent buttonJustPressedComponent = ButtonJustPressedComponent.mapper.get(entity);

        if (keyPressedComponent.keys.contains(Input.Keys.A, false)){
            EntityBuilder.createComponent(entity, AngularImpulseComponent.class).impulse = 0.1f;
        }

        if (keyPressedComponent.keys.contains(Input.Keys.D, false)){
            EntityBuilder.createComponent(entity, AngularImpulseComponent.class).impulse = -0.1f;
        }

        if (keyPressedComponent.keys.contains(Input.Keys.W, false)){
            tankComponent.bodyDirection.nor();
            EntityBuilder.createComponent(entity, ForceComponent.class).init(new Vector2(), tankComponent.bodyDirection.scl(25f), true);
        }
        if (keyPressedComponent.keys.contains(Input.Keys.S, false)){
            tankComponent.bodyDirection.nor();
            EntityBuilder.createComponent(entity, ForceComponent.class).init(new Vector2(), tankComponent.bodyDirection.scl(-20f), true);
        }

        if (buttonJustPressedComponent.buttons.contains(Input.Buttons.LEFT, false)){
            TankBulletEntityBuilder.create(tankComponent.firePosition, tankComponent.fireDirection, 0.1f);
        }
    }
}
