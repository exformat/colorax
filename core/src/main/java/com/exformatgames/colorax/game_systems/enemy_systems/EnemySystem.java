package com.exformatgames.colorax.game_systems.enemy_systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
import com.exformatgames.colorax.components.enemy_components.EnemyComponent;
import com.exformatgames.colorax.components.PLayerComponent;
import com.exformatgames.colorax.components.weapon_components.WeaponComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.ForceComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class EnemySystem extends IteratingSystem {

    private ImmutableArray<Entity> array; //TODO not enemy

    public EnemySystem() {
        super(Family.all(EnemyComponent.class).get());
    }

    @Override
    public void startProcessing() {
        array = getEngine().getEntitiesFor(Family.one(PLayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        EnemyComponent enemyComponent = EnemyComponent.mapper.get(entity);
        WeaponComponent weaponComponent = WeaponComponent.mapper.get(entity);
        SizeComponent sizeComponent = SizeComponent.mapper.get(entity);

        AnimationsComponent animationsComponent = AnimationsComponent.mapper.get(entity);
        AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);

        if(enemyComponent.target == null){
            enemyComponent.target = array.random();
        }

        if (array.contains(enemyComponent.target, false)){
            PositionComponent playerPositionComponent = PositionComponent.mapper.get(enemyComponent.target);
            PositionComponent enemyPositionComponent = PositionComponent.mapper.get(entity);
            SizeComponent playerSizeComponent = SizeComponent.mapper.get(enemyComponent.target);

            Vector2 dir = Pools.obtain(Vector2.class);
            dir.set(playerPositionComponent.x + playerSizeComponent.halfWidth, playerPositionComponent.y + playerSizeComponent.halfHeight);
            dir.sub(enemyPositionComponent.x + sizeComponent.halfWidth, enemyPositionComponent.y + sizeComponent.halfHeight);

            float len = dir.len();

            dir.nor();
            dir.scl(enemyComponent.force);

            enemyComponent.weaponPosition.setAngleRad(dir.angleRad());

            weaponComponent.active = len < weaponComponent.radius;
            weaponComponent.position.set(enemyComponent.weaponPosition.x + enemyPositionComponent.x + sizeComponent.halfWidth, enemyComponent.weaponPosition.y + enemyPositionComponent.y + sizeComponent.halfHeight);
            weaponComponent.dir.set(dir);

            if ( ! weaponComponent.active){
                EntityBuilder.createComponent(entity, ForceComponent.class).init(dir);
                if ( ! animationComponent.name.equals("WALK")){
                    animationComponent.init("WALK", 1f / 8f, animationsComponent.animations.get("WALK"), Animation.PlayMode.LOOP, 0.01f);
                }
            } else if( ! animationComponent.name.equals("IDLE")){
                animationComponent.init("IDLE", 1f / 8f, animationsComponent.animations.get("IDLE"), Animation.PlayMode.LOOP, 0.01f);
            }

            RotationComponent rotationComponent = RotationComponent.mapper.get(entity);
            if (rotationComponent == null) {
                EntityBuilder.createComponent(entity, RotationComponent.class).degress = dir.angleDeg();
            }else {
                rotationComponent.degress = dir.angleDeg();
            }


            Pools.free(dir);
        }
    }
}
