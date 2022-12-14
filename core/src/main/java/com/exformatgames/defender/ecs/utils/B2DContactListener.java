package com.exformatgames.defender.ecs.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.exformatgames.defender.ecs.engine.EntityBuilder;
import com.exformatgames.defender.ecs.engine.components.box2d.contact_components.BeginContactComponent;
import com.exformatgames.defender.ecs.engine.components.box2d.contact_components.EndContactComponent;

public class B2DContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        EntityBuilder.createComponent((Entity) a.getUserData(), BeginContactComponent.class).init((Entity) b.getUserData(), contact);
        EntityBuilder.createComponent((Entity) b.getUserData(), BeginContactComponent.class).init((Entity) a.getUserData(), contact);
    }

    @Override
    public void endContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        EntityBuilder.createComponent((Entity) a.getUserData(), EndContactComponent.class).init((Entity) b.getUserData(), contact);
        EntityBuilder.createComponent((Entity) b.getUserData(), EndContactComponent.class).init((Entity) a.getUserData(), contact);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}