package com.exformatgames.colorax.components.enemy_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Array;

public class WaveComponent implements Component {

    public Array<EnemySpawnComponent> spawnComponentArray = new Array<>();

    public static ComponentMapper<WaveComponent> mapper = ComponentMapper.getFor(WaveComponent.class);
}
