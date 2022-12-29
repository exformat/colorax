package com.exformatgames.defender.ecs.engine.systems.util_system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class MultiThreadSystem extends EntitySystem {

    private Family family;
    protected ImmutableArray<Entity> entities;
    private Array<Array<Entity>> matrix = new Array<>();
    private Array<Future> futures = new Array<>();

    private final int THREADS;

    protected static ExecutorService executorService;

    public MultiThreadSystem (Family family, int priority, int threads) {
        super(priority);
        this.family = family;
        this.THREADS = threads;

        for (int i = 0; i < THREADS; i++) {
            Array<Entity> entityArray = new Array<>();
            matrix.add(entityArray);
        }

        executorService = Executors.newFixedThreadPool(THREADS);
    }

    public MultiThreadSystem (Family family, int threads) {
        super();
        this.family = family;
        this.THREADS = threads;

        for (int i = 0; i < THREADS; i++) {
            Array<Entity> entityArray = new Array<>();
            matrix.add(entityArray);
        }

        executorService = Executors.newFixedThreadPool(THREADS);
    }

    public MultiThreadSystem (Family family) {
        super();
        this.family = family;
        this.THREADS = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < THREADS; i++) {
            Array<Entity> entityArray = new Array<>();
            matrix.add(entityArray);
        }

        executorService = Executors.newCachedThreadPool();
    }

    public void startProcessing() {}
    public void endProcessing() {}

    @Override
    public void update(float deltaTime) {
        startProcessing();
        //System.out.println("\nstart processing");
        int step = entities.size() / THREADS;

        int counter = 1;

        for (Array<Entity> array: matrix) {
            array.clear();
        }

        for (int i = 0; i < entities.size(); ++i) {
            if (i > counter * step){
                counter++;
            }
            int arrayIndex = counter - 1;
            if (arrayIndex < matrix.size){
                matrix.get(arrayIndex).add(entities.get(i));
            }
            else {
                matrix.first().add(entities.get(i));
            }
        }

        futures.clear();

        for (int i = 0; i < matrix.size; i++) {
            Array<Entity> array = matrix.get(i);

            futures.add(processRunnable(array, deltaTime));
        }

        waitThreads();
        endProcessing();
    }

    private Future processRunnable(Array<Entity> entities, float deltaTime){
        return executorService.submit(() -> {
            //System.out.println("thread: " + Thread.currentThread().getId() + " entities: " + entities.size);
            for (Entity entity: entities) {
                processEntity(entity, deltaTime);
            }
            //System.out.println("end process thread, id: " + Thread.currentThread().getId());
        });
    }

    private void waitThreads(){
        boolean wait = true;
        while (wait){
            int x = 0;
            for (int i = 0; i < futures.size; i++) {
                if (futures.get(i).isDone()){
                    x++;
                }
            }

            if (x >= futures.size){
                wait = false;
            }
        }
        //System.out.println("end wait \n");
    }

    protected abstract void processEntity(Entity entity, float deltaTime);

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(family);
    }

    public ImmutableArray<Entity> getEntities () {
        return entities;
    }

    public Family getFamily () {
        return family;
    }

    public void setFamily (Family family) {
        this.family = family;
    }
}