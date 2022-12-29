package com.exformatgames.arcanoid;

import com.badlogic.gdx.Game;
import com.exformatgames.arcanoid.screens.GameScreen;

public class ArcanoidGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
