package com.exformatgames.colorax.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.exformatgames.colorax.components.enemy_components.EnemySpawnComponent;
import com.exformatgames.defender.ecs.engine.EntityBuilder;

public class WaveEnemyEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
    }

    public void create(TypeEnemy typeEnemy) {

        switch (typeEnemy){
            case SOLDIER:{
                createComponent(EnemySpawnComponent.class).init(new SoldierEnemyBuilder(), 1, 5, 10, 10);
                break;
            }
            case TROOPER:{
                createComponent(EnemySpawnComponent.class).init(new TrooperEnemyBuilder(), 1, 5, 15, 10);
                break;
            }
            case HEAVY:{
                createComponent(EnemySpawnComponent.class).init(new HeavyEnemyComponent(), 1, 5, 20, 10);
                break;
            }
        }

    }

    public void create(TypeEnemy typeEnemy, Vector2 position,  int count, float interval) {

        switch (typeEnemy){
            case SOLDIER:{
                createComponent(EnemySpawnComponent.class).init(new SoldierEnemyBuilder(), count, interval, position.x, position.y);
                break;
            }
            case TROOPER:{
                createComponent(EnemySpawnComponent.class).init(new TrooperEnemyBuilder(), count, interval, position.x, position.y);
                break;
            }
            case HEAVY:{
                createComponent(EnemySpawnComponent.class).init(new HeavyEnemyComponent(), count, interval, position.x, position.y);
                break;
            }
        }

    }

    public enum TypeEnemy{
        SOLDIER,
        TROOPER,
        HEAVY;
    }
}
