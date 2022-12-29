package com.exformatgames.defender.ecs.test;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class TestSTSystem extends IteratingSystem {


    long startTime = 0;
    long endTime = 0;
    long deltaTime = 0;

    public TestSTSystem(){
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

        System.out.println("ST sec: " + deltaTime / 1000000000d);

        new TestMTEntity().create();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float atan = 0;
        for (int i = 0; i < 2_00_000; i++) {
            atan += (float) Math.tan(i);
        }

        //ComponentMapper.getFor(TestMTComponent.class).get(entity).number = (int)atan;
    }
}
