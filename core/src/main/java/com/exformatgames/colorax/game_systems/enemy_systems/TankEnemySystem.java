package com.exformatgames.colorax.game_systems.enemy_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
import com.exformatgames.colorax.components.PLayerComponent;
import com.exformatgames.colorax.components.enemy_components.TankEnemyComponent;
import com.exformatgames.colorax.components.weapon_components.WeaponComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.AngularImpulseComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.BodyComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.ForceComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.SizeComponent;

public class TankEnemySystem extends IteratingSystem {

    private ImmutableArray<Entity> array; //TODO not enemy

    public TankEnemySystem() {
        super(Family.one(TankEnemyComponent.class).get());
    }

    @Override
    public void startProcessing() {
        array = getEngine().getEntitiesFor(Family.one(PLayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TankEnemyComponent tankEnemyComponent = TankEnemyComponent.mapper.get(entity);
        WeaponComponent weaponComponent = WeaponComponent.mapper.get(entity);
        SizeComponent sizeComponent = SizeComponent.mapper.get(entity);
        BodyComponent bodyComponent = BodyComponent.mapper.get(entity);

        tankEnemyComponent.direction.setAngleRad(bodyComponent.body.getAngle());

        if(tankEnemyComponent.target == null){
            tankEnemyComponent.target = array.random();
        }

        if (array.contains(tankEnemyComponent.target, false)){
            PositionComponent playerPositionComponent = PositionComponent.mapper.get(tankEnemyComponent.target);
            PositionComponent enemyPositionComponent = PositionComponent.mapper.get(entity);
            SizeComponent playerSizeComponent = SizeComponent.mapper.get(tankEnemyComponent.target);

            Vector2 dir = Pools.obtain(Vector2.class);
            dir.set(playerPositionComponent.x + playerSizeComponent.halfWidth, playerPositionComponent.y + playerSizeComponent.halfHeight);
            dir.sub(enemyPositionComponent.x + sizeComponent.halfWidth, enemyPositionComponent.y + sizeComponent.halfHeight);

            float len = dir.len();

            dir.nor();

            float refAngle = dir.angleDeg(tankEnemyComponent.direction);

            if (refAngle > 2) {
                EntityBuilder.createComponent(entity, AngularImpulseComponent.class).impulse = tankEnemyComponent.angularForce;
            }

            if (refAngle > 180) {
                EntityBuilder.createComponent(entity, AngularImpulseComponent.class).impulse = -tankEnemyComponent.angularForce;
            }

            tankEnemyComponent.direction.nor();
            EntityBuilder.createComponent(entity, ForceComponent.class).init(new Vector2(), tankEnemyComponent.direction.scl(tankEnemyComponent.force), true);

            tankEnemyComponent.weaponPosition.setAngleRad(tankEnemyComponent.direction.angleRad());

            weaponComponent.active = len < weaponComponent.radius;
            weaponComponent.position.set(tankEnemyComponent.weaponPosition.x, tankEnemyComponent.weaponPosition.y);
            weaponComponent.position.setAngleRad(dir.angleRad());
            weaponComponent.position.add(enemyPositionComponent.x, enemyPositionComponent.y);
            weaponComponent.position.add(sizeComponent.halfWidth, sizeComponent.halfHeight);

            tankEnemyComponent.direction.nor();
            weaponComponent.dir.set(dir.nor());

            if (weaponComponent.active){
                SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity).spriteComponentArray.get(2);
                spriteComponent.offsetRotation = dir.angleDeg() - bodyComponent.oldRotation;
            }

            Pools.free(dir);
        }
    }
}
