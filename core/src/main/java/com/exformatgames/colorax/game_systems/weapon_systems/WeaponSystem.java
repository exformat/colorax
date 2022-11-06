package com.exformatgames.colorax.game_systems.weapon_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.colorax.components.weapon_components.WeaponComponent;

public class WeaponSystem extends IteratingSystem {


    public WeaponSystem() {
        super(Family.one(WeaponComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        WeaponComponent weaponComponent = WeaponComponent.mapper.get(entity);
        if (weaponComponent.active && (weaponComponent.timer -= deltaTime) < 0){
            weaponComponent.timer = 1f / weaponComponent.DPS;

            weaponComponent.bulletBuilder.create(weaponComponent.position, weaponComponent.dir);
        }
    }
}
