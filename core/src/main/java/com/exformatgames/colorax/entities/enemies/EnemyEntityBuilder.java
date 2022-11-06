package com.exformatgames.colorax.entities.enemies;

import com.exformatgames.defender.ecs.engine.EntityBuilder;

public abstract class EnemyEntityBuilder extends EntityBuilder {

    public abstract void create(float x, float y);
}
