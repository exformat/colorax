package com.exformatgames.colorax.game_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
import com.exformatgames.colorax.components.TankComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.RayComponent;
import com.exformatgames.defender.ecs.engine.components.input_components.MouseComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class RotateTankSystem extends IteratingSystem {

    public RotateTankSystem() {
        super(Family.one(TankComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TankComponent tankComponent = TankComponent.mapper.get(entity);

        MouseComponent mouseComponent = MouseComponent.mapper.get(entity);
        RayComponent rayComponent = RayComponent.mapper.get(entity);

        PositionComponent positionComponent = PositionComponent.mapper.get(entity);
        SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
        SizeComponent sizeComponent = SizeComponent.mapper.get(entity);
        BodyComponent bodyComponent = BodyComponent.mapper.get(entity);

        tankComponent.bodyDirection.setAngleRad(bodyComponent.body.getAngle()).rotate90(1);

        tankComponent.turretTarget.set(mouseComponent.position);

        Vector2 dir = Pools.obtain(Vector2.class);
        dir.set(positionComponent.x, positionComponent.y);

        tankComponent.turretTarget.sub(positionComponent.x + sizeComponent.halfWidth + tankComponent.turretPosition.x,
                                       positionComponent.y + tankComponent.turretPosition.y + sizeComponent.halfHeight);

        dir.rotateDeg(tankComponent.turretTarget.angleDeg(dir)).nor();

        tankComponent.turretTarget.add(positionComponent.x + sizeComponent.halfWidth + tankComponent.turretPosition.x,
                                       positionComponent.y + tankComponent.turretPosition.y + sizeComponent.halfHeight);

        tankComponent.turretPosition.setAngleDeg(spriteComponent.rotation + 90);

        tankComponent.firePosition.set(1.20f, 0);
        tankComponent.firePosition.rotateDeg(dir.angleDeg());

        tankComponent.firePosition.add(tankComponent.turretPosition.x + sizeComponent.halfWidth,
                                       tankComponent.turretPosition.y + sizeComponent.halfHeight);


        tankComponent.firePosition.add(positionComponent.x, positionComponent.y);
        tankComponent.fireDirection.setAngleRad(dir.angleRad());

        rayComponent.init(tankComponent.firePosition, tankComponent.turretTarget);

        spriteComponent.spriteComponentArray.get(1).offsetRotation = dir.angleDeg() - tankComponent.bodyDirection.angleDeg();
        spriteComponent.spriteComponentArray.get(1).setOffsetXY(tankComponent.turretPosition.x + sizeComponent.halfWidth - 0.465f,
                                                                tankComponent.turretPosition.y + sizeComponent.halfHeight- 0.5633f);

        Pools.free(dir);
    }
}
