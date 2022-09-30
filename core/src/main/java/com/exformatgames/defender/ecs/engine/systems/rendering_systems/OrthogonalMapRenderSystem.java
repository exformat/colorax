package com.exformatgames.defender.ecs.engine.systems.rendering_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.exformatgames.defender.ecs.engine.components.rendering_components.OrthoMapRenderComponent;
import com.exformatgames.defender.ecs.test.TestComponent;

public class OrthogonalMapRenderSystem extends IteratingSystem {

    private final OrthographicCamera camera;

    public OrthogonalMapRenderSystem(OrthographicCamera camera) {
        super(Family.one(TestComponent.class).get());
        this.camera = camera;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        OrthoMapRenderComponent mapRenderComponent = OrthoMapRenderComponent.mapper.get(entity);

        mapRenderComponent.mapRenderer.setView(camera);
        mapRenderComponent.mapRenderer.render();
    }
}
