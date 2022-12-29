package com.exformatgames.arkanoid;

import com.badlogic.gdx.Game;
import com.exformatgames.arkanoid.screens.GameScreen;

public class ArcanoidGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }
}
