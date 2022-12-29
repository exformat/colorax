package com.exformatgames.defender.ecs.test;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.exformatgames.defender.ecs.engine.systems.util_system.MultiThreadSystem;

import java.util.concurrent.Future;

public class TestMTSystem extends MultiThreadSystem {

    long startTime = 0;
    long endTime = 0;
    long deltaTime = 0;

    public TestMTSystem(){
        super(Family.all(TestMTComponent.class).get());
    }

    @Override
    public void startProcessing() {
        startTime = System.nanoTime();
    }

    @Override
    public void endProcessing() {
        endTime = System.nanoTime();
        deltaTime = endTime - startTime;


        System.out.println("MT entities: " + entities.size());
        System.out.println("MT sec: " + deltaTime / 1000000000d);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float atan = 0;
        for (int i = 0; i < 2_00_000; i++) {
            atan += (float) Math.tan(i);
        }
        if (MathUtils.randomBoolean()){
            entity.remove(TestMTComponent.class);
        }
        else {
            new TestMTEntity().create();
        }

        ComponentMapper.getFor(TestMTComponent.class).get(entity).number = (int)atan;
    }
}
