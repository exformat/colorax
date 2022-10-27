package com.exformatgames.colorax.game_systems.enemy_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
import com.exformatgames.colorax.components.EnemyComponent;
import com.exformatgames.colorax.components.PLayerComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.ForceComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.LinearImpulseComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.RotationComponent;

public class EnemySystem extends IteratingSystem {

    private ImmutableArray<Entity> array;

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
        if(enemyComponent.target == null){
            enemyComponent.target = array.random();
        }

        if (array.contains(enemyComponent.target, false)){
            PositionComponent playerPositionComponent = PositionComponent.mapper.get(enemyComponent.target);
            PositionComponent enemyPositionComponent = PositionComponent.mapper.get(entity);

            Vector2 dir = Pools.obtain(Vector2.class);
            dir.set(playerPositionComponent.x, playerPositionComponent.y);
            dir.sub(enemyPositionComponent.x, enemyPositionComponent.y);
            dir.nor();
            dir.scl(enemyComponent.force);

            EntityBuilder.createComponent(entity, ForceComponent.class).init(dir);

            RotationComponent rotationComponent = RotationComponent.mapper.get(entity);
            if (rotationComponent == null) {
                EntityBuilder.createComponent(entity, RotationComponent.class).degress = dir.angleDeg();
            }else {
                rotationComponent.degress = dir.angleDeg() + 90;
            }


            Pools.free(dir);
        }
    }
}
