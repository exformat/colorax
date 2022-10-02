package com.exformatgames.defender.ecs.engine.systems.input_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.exformatgames.defender.Constants;
import com.exformatgames.defender.ecs.engine.components.input_components.button_event_components.ButtonJustPressedComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.button_event_components.ButtonPressedComponent;

public class MouseInputSystem extends IteratingSystem {

    private final Array<Integer> pressedButtons = new Array<>();
    private final Array<Integer> justPressedButtons = new Array<>();

    private final Vector3 mousePosition = new Vector3();

    private final OrthographicCamera camera;

    public MouseInputSystem(OrthographicCamera camera) {
        super(Family.one(ButtonPressedComponent.class, ButtonJustPressedComponent.class).get());

        this.camera = camera;
        if (Constants.TARGET_BUTTONS.isEmpty()){
            Constants.TARGET_BUTTONS.addAll(Constants.DEFAULT_BUTTONS);
        }
    }

    @Override
    public void startProcessing() {
        mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePosition);

        pressedButtons.clear();
        justPressedButtons.clear();

        for (int button: Constants.TARGET_BUTTONS){
            if (Gdx.input.isButtonPressed(button)){
                pressedButtons.add(button);
            }
            if (Gdx.input.isButtonJustPressed(button)){
                justPressedButtons.add(button);
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ButtonPressedComponent buttonPressedComponent = ButtonPressedComponent.mapper.get(entity);
        ButtonJustPressedComponent justPressedComponent = ButtonJustPressedComponent.mapper.get(entity);

        if (buttonPressedComponent != null) {
            buttonPressedComponent.buttons.clear();
            buttonPressedComponent.buttons.addAll(pressedButtons);
            buttonPressedComponent.mousePosition.set(mousePosition.x, mousePosition.y);
        }

        if (justPressedComponent != null) {
            justPressedComponent.buttons.clear();
            justPressedComponent.buttons.addAll(justPressedButtons);
            justPressedComponent.mousePosition.set(mousePosition.x, mousePosition.y);
        }
    }
}
