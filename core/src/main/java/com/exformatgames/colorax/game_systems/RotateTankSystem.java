package com.exformatgames.colorax.game_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
import com.exformatgames.colorax.components.TankComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class RotateTankSystem extends IteratingSystem {

    public RotateTankSystem() {
        super(Family.one(TankComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TankComponent tankComponent = TankComponent.mapper.get(entity);

        PositionComponent positionComponent = PositionComponent.mapper.get(entity);
        SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
        SizeComponent sizeComponent = SizeComponent.mapper.get(entity);

        Vector2 dir = Pools.obtain(Vector2.class);
        dir.set(positionComponent.x, positionComponent.y);

        tankComponent.turretTarget.sub(positionComponent.x + sizeComponent.halfWidth + tankComponent.turretPosition.x,
                                       positionComponent.y + tankComponent.turretPosition.y + sizeComponent.halfHeight);

        dir.rotateDeg(tankComponent.turretTarget.angleDeg(dir)).nor();

        tankComponent.turretTarget.add(positionComponent.x + sizeComponent.halfWidth + tankComponent.turretPosition.x,
                                       positionComponent.y + tankComponent.turretPosition.y + sizeComponent.halfHeight);

        EntityBuilder.createComponent(entity, RotateComponent.class).degress = 1;
        tankComponent.turretPosition.setAngleDeg(spriteComponent.rotation + 90);

        spriteComponent.spriteComponentArray.get(1).rotation(dir.angleDeg() - 90);
        spriteComponent.spriteComponentArray.get(1).setOffsetXY(tankComponent.turretPosition.x + sizeComponent.halfWidth - 46.5f,
                                                                tankComponent.turretPosition.y + sizeComponent.halfHeight- 56.33f);

        Pools.free(dir);
    }
}
