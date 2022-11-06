package com.exformatgames.colorax.game_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pools;
import com.exformatgames.colorax.components.weapon_components.DamageComponent;
import com.exformatgames.colorax.components.weapon_components.ExplosionComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.AABBAnswerComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.PositionComponent;

public class ExplosionSystem extends IteratingSystem {

    public ExplosionSystem() {
        super(Family.all(ExplosionComponent.class, AABBAnswerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AABBAnswerComponent aabbAnswerComponent = AABBAnswerComponent.mapper.get(entity);
        PositionComponent positionComponent = PositionComponent.mapper.get(entity);
        ExplosionComponent explosionComponent = ExplosionComponent.mapper.get(entity);

        for (Body body: aabbAnswerComponent.bodies) {
            if (! body.isBullet()){
                Vector2 dir = Pools.obtain(Vector2.class);
                dir.set(body.getPosition());

                dir.sub(positionComponent.x, positionComponent.y);

                float lenNor = dir.len() / explosionComponent.radius;

                float imp = explosionComponent.impulse * lenNor;
                float dmg = explosionComponent.damage * lenNor;
                dir.nor();
                dir.scl(imp);

                body.applyForceToCenter(dir, true);

                Entity targetEntity = (Entity) body.getUserData();
                EntityBuilder.createComponent(targetEntity, DamageComponent.class).damage = dmg;
                Pools.free(dir);
            }
        }
        aabbAnswerComponent.bodies.clear();
        entity.remove(AABBAnswerComponent.class);
    }
}
