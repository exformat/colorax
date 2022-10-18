package com.exformatgames.colorax;

import com.badlogic.gdx.*;
import com.exformatgames.colorax.screens.TestGameScreen;

public class ColoraxGame extends Game {

	@Override
	public void create() {
		setScreen(new TestGameScreen(this));
	}
}