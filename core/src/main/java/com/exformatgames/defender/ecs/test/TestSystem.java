package com.exformatgames.defender.ecs.test;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.LinearImpulseComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.button_event_components.ButtonJustPressedComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.key_events.KeyPressedComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.CullingComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.gesture_components.GesturePanComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.ZIndexComponent;
import com.exformatgames.defender.ecs.engine.components.tiled_components.TiledMapComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.LinearVelocityComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.NewPositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.TranslateComponent;
import com.exformatgames.defender.ecs.engine.components.util_components.RemoveEntityComponent;

public class TestSystem extends IteratingSystem {

    private TextureRegion region;

    public TestSystem() {
        super(Family.one(TestComponent.class).get());

        region = new TextureRegion(new Texture("test_texture.png"));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CullingComponent cullingComponent = CullingComponent.mapper.get(entity);
        TranslateComponent translateComponent = getEngine().createComponent(TranslateComponent.class);

        GesturePanComponent gesturePanComponent = GesturePanComponent.mapper.get(entity);
        ButtonJustPressedComponent buttonJustPressedComponent = ButtonJustPressedComponent.mapper.get(entity);
        KeyPressedComponent keyPressedComponent = KeyPressedComponent.mapper.get(entity);

        PositionComponent positionComponent = PositionComponent.mapper.get(entity);

        TiledMapComponent tiledMapComponent = TiledMapComponent.mapper.get(entity);

        if(gesturePanComponent != null){
            translateComponent.x = gesturePanComponent.delta.x;
            translateComponent.y = gesturePanComponent.delta.y;
            entity.add(translateComponent);
        }

        if (! cullingComponent.inViewport){
            entity.add(getEngine().createComponent(NewPositionComponent.class).init(0, 0));
        }

        if (keyPressedComponent.keys.contains(Input.Keys.W, false)){
            //translateComponent.y += 50 * deltaTime;
            EntityBuilder.createComponent(entity, LinearImpulseComponent.class).init(0, 100, 0, 0);
        }
        if (keyPressedComponent.keys.contains(Input.Keys.A, false)){
            //translateComponent.x -= 50 * deltaTime;
            LinearImpulseComponent linearImpulseComponent = LinearImpulseComponent.mapper.get(entity);
            if (linearImpulseComponent == null){
                EntityBuilder.createComponent(entity, LinearImpulseComponent.class).init(-100, 0, 0, 0);
            }
            else {
                linearImpulseComponent.impulseX += -10;
            }
        }
        if (keyPressedComponent.keys.contains(Input.Keys.S, false)){
            //translateComponent.y -= 50 * deltaTime;

            LinearImpulseComponent linearImpulseComponent = LinearImpulseComponent.mapper.get(entity);
            if (linearImpulseComponent == null){
                EntityBuilder.createComponent(entity, LinearImpulseComponent.class).init(0, -100, 0, 0);
            }
            else {
                linearImpulseComponent.impulseY += -10;
            }
        }
        if (keyPressedComponent.keys.contains(Input.Keys.D, false)){

            LinearImpulseComponent linearImpulseComponent = LinearImpulseComponent.mapper.get(entity);
            if (linearImpulseComponent == null){
                EntityBuilder.createComponent(entity, LinearImpulseComponent.class).init(100, 0, 0, 0);
            }
            else {
                linearImpulseComponent.impulseX += 10;
            }
        }

        if (buttonJustPressedComponent.buttons.contains(Input.Buttons.LEFT, false)){

            Vector2 dir = Pools.obtain(Vector2.class);
            dir.set(positionComponent.x, positionComponent.y);
            buttonJustPressedComponent.mousePosition.sub(positionComponent.x, positionComponent.y);
            dir.rotateDeg(buttonJustPressedComponent.mousePosition.angleDeg(dir)).nor().scl(300);
            buttonJustPressedComponent.mousePosition.add(positionComponent.x, positionComponent.y);

            Entity bulletEntity = getEngine().createEntity();
            EntityBuilder.createComponent(bulletEntity, LinearVelocityComponent.class).init(dir.x, dir.y);
            EntityBuilder.createComponent(bulletEntity, NewPositionComponent.class).init(positionComponent.x, positionComponent.y);
            EntityBuilder.createComponent(bulletEntity, SpriteComponent.class).init(region, 0.2f);
            EntityBuilder.createComponent(bulletEntity, ZIndexComponent.class).zIndex = 1;
            EntityBuilder.createComponent(bulletEntity, RemoveEntityComponent.class).timer = 0.2f;

            Pools.free(dir);
            getEngine().addEntity(bulletEntity);
        }
    }
}
