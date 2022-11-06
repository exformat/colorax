package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ArrayMap;

public class AnimationsComponent implements Component {

    public ArrayMap<String, Animation<TextureAtlas.AtlasRegion>> animations = new ArrayMap<>();

    public AnimationsComponent init(String[] keys, Animation<TextureAtlas.AtlasRegion>[] values){
        animations.clear();
        for (int i = 0; i < keys.length; i++) {
            animations.put(keys[i], values[i]);
        }

        return this;
    }

    public static ComponentMapper<AnimationsComponent> mapper = ComponentMapper.getFor(AnimationsComponent.class);
}
