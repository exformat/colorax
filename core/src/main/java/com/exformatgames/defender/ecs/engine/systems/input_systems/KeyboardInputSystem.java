package com.exformatgames.defender.ecs.engine.systems.input_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.exformatgames.defender.Constants;
import com.exformatgames.defender.ecs.engine.components.input_components.key_events.KeyJustPressedComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.key_events.KeyPressedComponent;

public class KeyboardInputSystem extends IteratingSystem {

    private final Array<Integer> pressedKeys = new Array<>();
    private final Array<Integer> justPressedKeys = new Array<>();

    public KeyboardInputSystem() {
        super(Family.one(KeyPressedComponent.class, KeyJustPressedComponent.class).get());

        if (Constants.TARGET_KEYS.isEmpty()){
            Constants.TARGET_KEYS.addAll(Constants.DEFAULT_KEYS);
        }
    }

    @Override
    public void startProcessing() {

        pressedKeys.clear();
        justPressedKeys.clear();

        for (int key: Constants.TARGET_KEYS){
            if (Gdx.input.isKeyPressed(key)){
                pressedKeys.add(key);
            }
            if (Gdx.input.isKeyJustPressed(key)){
                justPressedKeys.add(key);
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        KeyPressedComponent pressedComponent = KeyPressedComponent.mapper.get(entity);
        KeyJustPressedComponent justPressedComponent = KeyJustPressedComponent.mapper.get(entity);

        if (pressedComponent != null) {
            pressedComponent.keys.clear();
            pressedComponent.keys.addAll(pressedKeys);
        }

        if (justPressedComponent != null) {
            justPressedComponent.keys.clear();
            justPressedComponent.keys.addAll(justPressedKeys);
        }
    }
}
