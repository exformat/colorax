package com.exformatgames.defender.ecs.engine.components.box2d.contact_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;

public class BeginContactComponent implements Component {

    public Entity contactEntity;
    public Contact contact;


    public BeginContactComponent init(Entity contactEntity, Contact contact) {
        this.contactEntity = contactEntity;
        this.contact = contact;

        return this;
    }

    public static ComponentMapper<BeginContactComponent> mapper = ComponentMapper.getFor(BeginContactComponent.class);

}
