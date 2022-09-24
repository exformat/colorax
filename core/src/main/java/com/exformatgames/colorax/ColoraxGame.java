package com.exformatgames.colorax;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.exformatgames.defender.ecs.test.TestCore;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ColoraxGame extends ApplicationAdapter {

	private FPSLogger fpsLogger;

	private TestCore testCore;

	@Override
	public void create() {

		fpsLogger = new FPSLogger();

		testCore = new TestCore();
		testCore.create(false, true);

		System.out.println("entities: " + testCore.getEngine().getEntities().size());
		int components = 0;

		for (int i = 0; i < testCore.getEngine().getEntities().size(); i++) {
			components += testCore.getEngine().getEntities().get(i).getComponents().size();
		}

		System.out.println("components: " + components);

		System.out.println("systems: " + testCore.getEngine().getSystems().size());



	}

	float counter = 0;

	@Override
	public void render() {
		//Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//testCore.update(Gdx.graphics.getDeltaTime());

		fpsLogger.log();
	}

	
	@Override
	public void dispose() {
		testCore.dispose();
	}
}