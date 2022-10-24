package com.exformatgames.defender.ecs.engine.components.box2d.contact_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;

public class EndContactComponent implements Component {

    public Entity contactEntity;
    public Contact contact;

    public EndContactComponent init(Entity contactEntity, Contact contact) {
        this.contactEntity = contactEntity;
        this.contact = contact;

        return this;
    }

    public static ComponentMapper<EndContactComponent> mapper = ComponentMapper.getFor(EndContactComponent.class);

}
