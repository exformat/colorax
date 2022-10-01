package com.exformatgames.defender.ecs.engine.systems.input_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.exformatgames.defender.Constants;
import com.exformatgames.defender.ecs.engine.components.input_components.key_events.KeyPressedComponent;

public class KeyInputSystem extends IteratingSystem {

    private final Array<Integer> pressedKeys = new Array<>();
    public KeyInputSystem() {
        super(Family.one(KeyPressedComponent.class).get());

        if (Constants.TARGET_KEYS.isEmpty()){
            Constants.TARGET_KEYS.addAll(Constants.DEFAULT_KEYS);
        }
    }

    @Override
    public void startProcessing() {

        pressedKeys.clear();

        for (int key: Constants.TARGET_KEYS){
            if (Gdx.input.isKeyPressed(key)){
                pressedKeys.add(key);
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        KeyPressedComponent keyPressedComponent = KeyPressedComponent.mapper.get(entity);
        keyPressedComponent.keys.clear();
        keyPressedComponent.keys.addAll(pressedKeys);
    }
}
