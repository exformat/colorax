package com.exformatgames.colorax.components.enemy_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.exformatgames.colorax.entities.enemies.EnemyEntityBuilder;

public class EnemySpawnComponent implements Component {

    public EnemyEntityBuilder enemyBuilder;
    public int count = 0;
    public float interval = 0;
    public float timer = 0;

    public float x = 0;
    public float y = 0;

    public EnemySpawnComponent init(EnemyEntityBuilder enemyBuilder, int count, float interval, float x, float y) {
        this.enemyBuilder = enemyBuilder;
        this.count = count;
        this.interval = interval;
        this.x = x;
        this.y = y;

        return this;
    }

    public static ComponentMapper<EnemySpawnComponent> mapper = ComponentMapper.getFor(EnemySpawnComponent.class);
}
