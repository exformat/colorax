package com.exformatgames.colorax.entities.weapons;

import com.badlogic.gdx.math.Vector2;
import com.exformatgames.defender.ecs.engine.EntityBuilder;

public abstract class ActionBulletContactBuilder extends EntityBuilder {

    public abstract void create(Vector2 position, Vector2 direction);
}
