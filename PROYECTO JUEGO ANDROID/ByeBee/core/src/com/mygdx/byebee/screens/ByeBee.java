package com.mygdx.byebee.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.byebee.screens.TitleScreen;

public class ByeBee extends Game {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "ByeBee";
	private TitleScreen titleScreen;
	private Level1 level1; // Solo para probar, luego lo borro

	@Override
	public void create () {
		//titleScreen = new TitleScreen();
		level1 = new Level1();
		setScreen(level1);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		level1.resize(width, height);
	}

	@Override
	public void dispose () {
		level1.dispose();
	}


}