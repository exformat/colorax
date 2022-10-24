package com.exformatgames.defender.ecs.utils;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Null;

public class Particles {

    private static final ArrayMap<String, ParticleEffectPool> particlesArrayMap = new ArrayMap<>();

    public static int PUT(String key, ParticleEffectPool value) {
        return particlesArrayMap.put(key, value);
    }

    @Null
    public static ParticleEffectPool GET(String key) {
        return particlesArrayMap.get(key);
    }

    public static ArrayMap<String, ParticleEffectPool> getParticlesArrayMap() {
        return particlesArrayMap;
    }
}
