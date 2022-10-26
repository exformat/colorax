package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.AABBAnswerComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.AABBQueryComponent;

public class AABBQuerySystem extends IteratingSystem {

    private final World world;
    private final QueryCallback queryCallback;

    private final Array<Body> bodies = new Array<>();

    public AABBQuerySystem(World world) {
        super(Family.all(AABBQueryComponent.class).get());

        this.world = world;

        queryCallback = fixture -> {
            bodies.add(fixture.getBody());
            return true;
        };
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodies.clear();

        AABBQueryComponent queryComponent = AABBQueryComponent.mapper.get(entity);

        world.QueryAABB(queryCallback, queryComponent.x, queryComponent.y, queryComponent.x2, queryComponent.y2);

        entity.remove(AABBQueryComponent.class);

        EntityBuilder.createComponent(entity, AABBAnswerComponent.class).bodies.addAll(bodies);
    }
}
